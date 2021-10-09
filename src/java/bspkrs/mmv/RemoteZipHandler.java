//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package bspkrs.mmv;

import java.net.*;
import java.util.*;
import java.io.*;
import java.security.*;
import java.util.zip.*;

public class RemoteZipHandler
{
    private static final String MMV_VERSION = "1.0.1";
    private final URL zipUrl;
    private final URL digestUrl;
    private final File localDir;
    private final String digestType;
    private final String zipFileName;
    
    public RemoteZipHandler(final String urlString, final File dir, final String digestType) throws MalformedURLException {
        this.zipUrl = new URL(urlString);
        if (digestType != null) {
            this.digestUrl = new URL(urlString + "." + digestType.toLowerCase());
        }
        else {
            this.digestUrl = null;
        }
        final String[] tokens = urlString.split("/");
        this.zipFileName = tokens[tokens.length - 1];
        this.localDir = dir;
        this.digestType = digestType;
    }
    
    public void checkRemoteZip() throws IOException, NoSuchAlgorithmException, DigestException {
        boolean fetchZip = true;
        String remoteHash = null;
        File digestFile = null;
        if (this.digestType != null) {
            remoteHash = loadTextFromURL(this.digestUrl, new String[] { "" })[0];
            if (!remoteHash.isEmpty()) {
                digestFile = new File(this.localDir, this.zipFileName + "." + this.digestType.toLowerCase());
                if (digestFile.exists()) {
                    final String existingHash = loadTextFromFile(digestFile, new String[] { "" })[0];
                    if (!existingHash.isEmpty() && remoteHash.equals(existingHash)) {
                        fetchZip = false;
                    }
                }
            }
        }
        if (fetchZip) {
            final File localZip = new File(this.localDir, this.zipFileName);
            if (localZip.exists()) {
                localZip.delete();
            }
            final OutputStream output = new FileOutputStream(localZip);
            try {
                final URLConnection uc = this.zipUrl.openConnection();
                uc.addRequestProperty("User-Agent", "MMV/1.0.1");
                final byte[] buffer = new byte[1024];
                try (final InputStream is = uc.getInputStream()) {
                    int bytesRead;
                    while ((bytesRead = is.read(buffer)) > 0) {
                        output.write(buffer, 0, bytesRead);
                    }
                }
            }
            finally {
                output.close();
            }
            if (this.digestType != null && !remoteHash.isEmpty()) {
                final String downloadHash = getFileDigest(new FileInputStream(localZip), this.digestType);
                if (!remoteHash.equals(downloadHash)) {
                    throw new DigestException("Remote digest does not match digest of downloaded file!");
                }
            }
            extractZip(localZip, this.localDir);
            if (localZip.exists()) {
                localZip.delete();
            }
            if (this.digestType != null && !remoteHash.isEmpty()) {
                if (digestFile.exists()) {
                    digestFile.delete();
                }
                digestFile.createNewFile();
                final PrintWriter out = new PrintWriter(new FileWriter(digestFile));
                out.print(remoteHash);
                out.close();
            }
        }
    }
    
    public static String[] loadTextFromURL(final URL url, final String[] defaultValue) {
        final List<String> arraylist = new ArrayList<String>();
        Scanner scanner = null;
        try {
            final URLConnection uc = url.openConnection();
            uc.addRequestProperty("User-Agent", "MMV/1.0.1");
            final InputStream is = uc.getInputStream();
            scanner = new Scanner(is, "UTF-8");
            while (scanner.hasNextLine()) {
                arraylist.add(scanner.nextLine());
            }
        }
        catch (Throwable e) {
            return defaultValue;
        }
        finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return arraylist.toArray(new String[arraylist.size()]);
    }
    
    public static String[] loadTextFromFile(final File file, final String[] defaultValue) {
        final ArrayList<String> lines = new ArrayList<String>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        }
        catch (FileNotFoundException e) {
            return defaultValue;
        }
        finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return lines.toArray(new String[lines.size()]);
    }
    
    public static String getFileDigest(final InputStream is, final String digestType) throws NoSuchAlgorithmException, IOException {
        final MessageDigest md = MessageDigest.getInstance(digestType);
        final byte[] dataBytes = new byte[1024];
        int nread = 0;
        while ((nread = is.read(dataBytes)) != -1) {
            md.update(dataBytes, 0, nread);
        }
        is.close();
        final byte[] mdbytes = md.digest();
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mdbytes.length; ++i) {
            sb.append(Integer.toString((mdbytes[i] & 0xFF) + 256, 16).substring(1));
        }
        return sb.toString();
    }
    
    public static void extractZip(final File zipFile, final File destDir) throws IOException {
        final byte[] buffer = new byte[1024];
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        final ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
        ZipEntry ze = zis.getNextEntry();
        try {
            while (ze != null) {
                final String fileName = ze.getName();
                final File newFile = new File(destDir, fileName);
                if (ze.isDirectory()) {
                    if (newFile.exists()) {
                        deleteDirAndContents(newFile);
                    }
                    newFile.mkdirs();
                }
                else {
                    if (newFile.exists()) {
                        newFile.delete();
                    }
                    if (newFile.getParentFile() != null && !newFile.getParentFile().exists()) {
                        newFile.getParentFile().mkdirs();
                    }
                    final FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                }
                ze = zis.getNextEntry();
            }
        }
        finally {
            zis.closeEntry();
            zis.close();
        }
    }
    
    public static boolean deleteDirAndContents(final File dir) {
        if (dir.isDirectory()) {
            final String[] children = dir.list();
            for (int i = 0; i < children.length; ++i) {
                final boolean success = deleteDirAndContents(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}
