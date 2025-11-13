package com.pnfsoftware.jeb.util.io;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.base.OSType;
import com.pnfsoftware.jeb.util.concurrent.ThreadUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.regex.Matcher;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class IO {
   private static final ILogger logger = GlobalLog.getLogger(IO.class);
   private static OSType OSTYPE = OSType.determine();
   private static boolean IS_WINDOWS = OSTYPE.isWindows();
   public static final int MAX_FILENAME_SIZE = 255;
   public static final int MAX_PATH_SIZE = 248;
   private static final String endName = "~1";
   private static String cwd0;
   private static File tempFolder = null;
   private static File safeTempFolder = null;
   private static Set windowsIllegalFileNames;
   private static boolean disablingMkdirsReplacement;

   public static String setCwd(String var0) {
      String var1 = getCwd();
      if (cwd0 == null) {
         cwd0 = var1;
      }

      System.setProperty("user.dir", var0);
      return var1;
   }

   public static String getCwd() {
      return Strings.safe(System.getProperty("user.dir"));
   }

   public static String getOriginalCwd() {
      if (cwd0 == null) {
         cwd0 = getCwd();
      }

      return cwd0;
   }

   public static boolean isFile(String var0) {
      File var1 = new File(var0);
      return var1.exists() && var1.isFile();
   }

   public static File createFolder(String var0) throws IOException {
      File var1 = new File(var0);
      if (var1.isDirectory()) {
         return var1;
      } else {
         mkdirs2(var1);
         return var1;
      }
   }

   public static boolean createDirectory(String var0) {
      return createDirectory(new File(var0));
   }

   public static boolean createDirectory(File var0) {
      try {
         return var0.mkdirs();
      } catch (Exception var2) {
         logger.catchingSilent(var2);
         return false;
      }
   }

   public static boolean deleteDirectory(String var0) {
      return deleteDirectory(new File(var0));
   }

   public static boolean deleteDirectory(File var0) {
      return deleteDirectoryParallel(var0, false);
   }

   private static void deleteDirectory(File var0, int[] var1) {
      File[] var2 = var0.listFiles();
      if (var2 != null) {
         for (File var6 : var2) {
            if (var6.isDirectory()) {
               deleteDirectory(var6, var1);
            } else if (!var6.delete()) {
               var1[0]++;
               return;
            }
         }
      }

      if (!var0.delete()) {
         var1[0]++;
      }
   }

   private static boolean deleteDirectoryParallel(File var0, boolean var1) {
      if (var0 != null && var0.isDirectory()) {
         AtomicBoolean var2 = new AtomicBoolean();
         ArrayList var3 = new ArrayList();
         ArrayList var4 = new ArrayList();

         try {
            Files.walkFileTree(var0.toPath(), Collections.emptySet(), Integer.MAX_VALUE, new IO$2(var3, var4));
         } catch (IOException var8) {
            var2.set(true);
         }

         var3.parallelStream().forEach(var1x -> {
            try {
               Files.delete(var1x);
            } catch (IOException var2x) {
               var2.set(true);
            }
         });
         if (var1) {
            if (var4.isEmpty()) {
               return false;
            }

            File var5 = ((Path)var4.remove(var4.size() - 1)).toFile();
            if (var0.equals(var5)) {
               return false;
            }
         }

         for (Path var6 : var4) {
            try {
               Files.delete(var6);
            } catch (IOException var7) {
               var2.set(true);
            }
         }

         return !var2.get();
      } else {
         return false;
      }
   }

   public static boolean deleteDirectoryContents(File var0) {
      return deleteDirectoryParallel(var0, true);
   }

   public static boolean deleteFile(File var0) {
      var0.setWritable(true);
      return var0.delete();
   }

   public static boolean renameFile(File var0, File var1, int var2) {
      boolean var3 = var0.renameTo(var1);
      if (var3) {
         return true;
      } else if (var2 == 0 || !var0.exists() || !var1.exists()) {
         return false;
      } else if (var2 == 1) {
         return deleteFile(var1) && var0.renameTo(var1);
      } else if (var2 == 2) {
         return deleteFile(var0);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public static List listFiles(String var0) {
      return listFiles(new File(var0));
   }

   public static List listFiles(File var0) {
      ArrayList var1 = new ArrayList();
      listFilesRecurse(var0, var1);
      return var1;
   }

   private static void listFilesRecurse(File var0, List var1) {
      if (var0.isDirectory()) {
         for (String var5 : safeList(var0)) {
            listFilesRecurse(new File(var0, var5), var1);
         }
      } else if (var0.isFile()) {
         var1.add(var0);
      }
   }

   public static String[] safeList(File var0) {
      String[] var1 = var0.list();
      return var1 == null ? new String[0] : var1;
   }

   public static String[] safeList(File var0, FilenameFilter var1) {
      String[] var2 = var0.list(var1);
      return var2 == null ? new String[0] : var2;
   }

   public static File[] safeListFiles(File var0) {
      File[] var1 = var0.listFiles();
      return var1 == null ? new File[0] : var1;
   }

   public static File[] safeListFiles(File var0, FileFilter var1) {
      File[] var2 = var0.listFiles(var1);
      return var2 == null ? new File[0] : var2;
   }

   public static boolean inFolder(File var0, File var1) {
      String var2 = abs(var0);
      String var3 = abs(var1);
      return var2.startsWith(var3 + File.separator);
   }

   public static File getHomeFolder() {
      return new File(System.getProperty("user.home"));
   }

   public static File getTempFolder() {
      return tempFolder;
   }

   public static File getSessionTemporaryFolder() {
      return safeTempFolder;
   }

   public static File createTempFolder(String var0) throws IOException {
      if (var0 == null) {
         var0 = "tmp-" + System.nanoTime();
      }

      File var1 = new File(tempFolder, var0);
      mkdirWarn(var1);
      return var1;
   }

   public static File createTempFile() throws IOException {
      return File.createTempFile("file-" + System.nanoTime(), null);
   }

   public static File createSafeTempFile() throws IOException {
      File var0 = new File(getSessionTemporaryFolder(), "file-" + System.nanoTime() + ".tmp");
      if (!var0.createNewFile()) {
         throw new IOException("Error creating safe temp file!");
      } else {
         return var0;
      }
   }

   public static File createTempFile(String var0, String var1) throws IOException {
      if (var0 == null) {
         var0 = "file-" + System.nanoTime();
      }

      return File.createTempFile(var0, var1);
   }

   public static File createTempFile(String var0) {
      return new File(getTempFolder(), var0);
   }

   public static File createTempFileNumbered(String var0) throws IOException {
      File var1 = getTempFolder();
      int var2 = 0;
      File var3 = new File(var1, var0);

      while (var3.exists() && var2 < 10000) {
         var3 = new File(getTempFolder(), var0 + "." + ++var2);
      }

      if (var2 >= 10000 && var3.exists()) {
         throw new IOException("Cannot create new temp file with wanted name " + var0);
      } else {
         return var3;
      }
   }

   public static boolean createFile(File var0, boolean var1) throws IOException {
      if (var1) {
         createFoldersForFile(var0);
      }

      return var0.createNewFile();
   }

   public static void createFoldersForFile(File var0) throws IOException {
      File var1 = var0.getAbsoluteFile().getParentFile();
      if (var1 != null) {
         if (var1.isDirectory()) {
            return;
         }

         mkdirs2(var1);
      }
   }

   public static void deleteDirectoryOnExit(File var0) {
      var0.deleteOnExit();
      File[] var1 = var0.listFiles();
      if (var1 != null) {
         for (File var5 : var1) {
            if (var5.isDirectory()) {
               deleteDirectoryOnExit(var5);
            } else {
               var5.deleteOnExit();
            }
         }
      }
   }

   public static void copyFile(File var0, File var1, boolean var2) throws IOException {
      if (!var0.isFile()) {
         throw new FileNotFoundException("Source file not found: " + var0);
      } else {
         if (var1.isDirectory()) {
            var1 = new File(var1, var0.getName());
         }

         if (var1.exists() && !var2) {
            throw new FileAlreadyExistsException("Copy would overwrite an existing file: " + var1);
         } else {
            try (
               BufferedInputStream var3 = new BufferedInputStream(new FileInputStream(var0));
               BufferedOutputStream var4 = new BufferedOutputStream(new FileOutputStream(var1));
            ) {
               copyStream(var3, var4, new byte[4096]);
            }
         }
      }
   }

   public static long copyStream(InputStream var0, OutputStream var1, byte[] var2) throws IOException {
      try {
         long var3 = 0L;

         int var5;
         while ((var5 = var0.read(var2)) != -1) {
            if (Thread.currentThread().isInterrupted()) {
               throw new InterruptedException("Interrupted during copy");
            }

            var1.write(var2, 0, var5);
            var3 += var5;
         }

         return var3;
      } catch (InterruptedException var6) {
         throw new IOException("Copy interrupted", var6);
      }
   }

   public static long copyStream(InputStream var0, OutputStream var1) throws IOException {
      return copyStream(var0, var1, new byte[4096]);
   }

   public static long copy(InputStream var0, OutputStream var1) throws IOException {
      return copyStream(var0, var1);
   }

   public static void writeFile(File var0, byte[] var1, int var2, int var3, boolean var4) throws IOException {
      if (var4) {
         File var5 = var0.getAbsoluteFile().getParentFile();
         if (var5 != null) {
            mkdirs2Ex(var5);
         }
      }

      try (DataOutputStream var10 = new DataOutputStream(new FileOutputStream(var0))) {
         var10.write(var1, var2, var3);
      }
   }

   public static void writeFile(File var0, byte[] var1, boolean var2) throws IOException {
      writeFile(var0, var1, 0, var1.length, var2);
   }

   public static void writeFile(File var0, byte[] var1, int var2, int var3) throws IOException {
      writeFile(var0, var1, var2, var3, false);
   }

   public static void writeFile(File var0, byte[] var1) throws IOException {
      writeFile(var0, var1, 0, var1.length, false);
   }

   public static void writeFile(File var0, String var1) throws IOException {
      writeFile(var0, var1.getBytes(Charset.defaultCharset()));
   }

   public static void writeFile(File var0, String var1, String var2) throws IOException {
      writeFile(var0, var1.getBytes(var2));
   }

   public static byte[] readFile(File var0, long var1) throws IOException {
      long var3 = var0.length();
      if ((var1 < 0L || var3 <= var1) && var3 <= 2147483647L) {
         byte[] var7;
         try (DataInputStream var5 = new DataInputStream(new FileInputStream(var0))) {
            byte[] var6 = new byte[(int)var3];
            var5.readFully(var6);
            var7 = var6;
         }

         return var7;
      } else {
         throw new IOException(Strings.ff("File is too large (%d bytes)", var3));
      }
   }

   public static byte[] readFile(File var0) throws IOException {
      return readFile(var0, -1L);
   }

   public static byte[] readFile(String var0) throws IOException {
      return readFile(new File(var0), -1L);
   }

   public static byte[] readInputStream(InputStream var0) throws IOException {
      if (var0 instanceof FileInputStream) {
         long var5 = ((FileInputStream)var0).getChannel().size();
         if (var5 > 2147483647L) {
            throw new IOException(Strings.ff("This method cannot read files larger than 2Gb: size=%d", var5));
         } else {
            byte[] var6 = new byte[(int)var5];
            int var4 = var0.read(var6);
            if (var4 != var5) {
               throw new IOException(Strings.ff("Unexpected amount of bytes read from file: %d != %d", var4, var5));
            } else {
               return var6;
            }
         }
      } else {
         ByteArrayOutputStream var1 = new ByteArrayOutputStream();
         byte[] var3 = new byte[16384];

         int var2;
         while ((var2 = var0.read(var3, 0, var3.length)) != -1) {
            var1.write(var3, 0, var2);
         }

         var1.flush();
         return var1.toByteArray();
      }
   }

   public static List readLines(InputStream var0, Charset var1) throws IOException {
      ArrayList var5;
      try (BufferedReader var2 = new BufferedReader(new InputStreamReader(var0, var1))) {
         ArrayList var3 = new ArrayList();

         String var4;
         while ((var4 = var2.readLine()) != null) {
            var3.add(var4);
         }

         var5 = var3;
      }

      return var5;
   }

   public static List readLines(InputStream var0) throws IOException {
      return readLines(var0, Charset.forName("UTF-8"));
   }

   public static List readLines(File var0, Charset var1) throws IOException {
      List var3;
      try (FileInputStream var2 = new FileInputStream(var0)) {
         var3 = readLines(var2, var1);
      }

      return var3;
   }

   public static List readLines(File var0) throws IOException {
      List var2;
      try (FileInputStream var1 = new FileInputStream(var0)) {
         var2 = readLines(var1, Charset.forName("UTF-8"));
      }

      return var2;
   }

   public static List readLinesSafe(File var0, Charset var1) {
      try {
         return readLines(var0, var1);
      } catch (IOException var3) {
         logger.catchingSilent(var3);
         return null;
      }
   }

   public static List readLinesSafe(File var0) {
      return readLinesSafe(var0, Charset.forName("UTF-8"));
   }

   public static void writeLines(OutputStream var0, List var1, Charset var2) throws IOException {
      try (BufferedWriter var3 = new BufferedWriter(new OutputStreamWriter(var0, var2))) {
         for (CharSequence var5 : var1) {
            var3.write(var5.toString());
            var3.newLine();
         }
      }
   }

   public static void writeLines(OutputStream var0, List var1) throws IOException {
      writeLines(var0, var1, Charset.forName("UTF-8"));
   }

   public static void writeLines(File var0, List var1, Charset var2) throws IOException {
      try (FileOutputStream var3 = new FileOutputStream(var0)) {
         writeLines(var3, var1, var2);
      }
   }

   public static void writeLines(File var0, List var1) throws IOException {
      writeLines(var0, var1, Charset.forName("UTF-8"));
   }

   public static boolean writeLinesSafe(OutputStream var0, List var1, Charset var2) {
      try {
         writeLines(var0, var1, var2);
         return true;
      } catch (IOException var4) {
         logger.catching(var4);
         return false;
      }
   }

   public static boolean writeLinesSafe(OutputStream var0, List var1) {
      return writeLinesSafe(var0, var1, Charset.forName("UTF-8"));
   }

   public static boolean writeLinesSafe(File var0, List var1, Charset var2) {
      try {
         writeLines(var0, var1, var2);
         return true;
      } catch (IOException var4) {
         logger.catchingSilent(var4);
         return false;
      }
   }

   public static boolean writeLinesSafe(File var0, List var1) {
      return writeLinesSafe(var0, var1, Charset.forName("UTF-8"));
   }

   public static int getFirstIntLE(String var0) {
      File var1 = new File(var0);

      try {
         int var3;
         try (LEDataInputStream var2 = new LEDataInputStream(new FileInputStream(var1))) {
            var3 = var2.readInt();
         }

         return var3;
      } catch (IOException var7) {
         return 0;
      }
   }

   public static short getFirstShortLE(String var0) {
      File var1 = new File(var0);

      try {
         short var3;
         try (LEDataInputStream var2 = new LEDataInputStream(new FileInputStream(var1))) {
            var3 = var2.readShort();
         }

         return var3;
      } catch (IOException var7) {
         logger.catchingSilent(var7);
         return 0;
      }
   }

   public static byte getFirstByte(String var0) {
      File var1 = new File(var0);

      try {
         byte var3;
         try (LEDataInputStream var2 = new LEDataInputStream(new FileInputStream(var1))) {
            var3 = var2.readByte();
         }

         return var3;
      } catch (IOException var7) {
         logger.catchingSilent(var7);
         return 0;
      }
   }

   public static boolean compressFolder(String var0, String var1) {
      File var2 = new File(var0);
      if (!var2.isDirectory()) {
         return false;
      } else {
         try {
            boolean var4;
            try (ZipOutputStream var3 = new ZipOutputStream(new FileOutputStream(new File(var1)))) {
               compressFolderRecurse(var3, var2, "/");
               var4 = true;
            }

            return var4;
         } catch (IOException var8) {
            logger.catchingSilent(var8);
            return false;
         }
      }
   }

   private static void compressFolderRecurse(ZipOutputStream var0, File var1, String var2) throws IOException {
      File var3 = new File(var1, var2);
      if (var3.isFile()) {
         ZipEntry var4 = new ZipEntry(var2);
         var0.putNextEntry(var4);
         byte[] var5 = readFile(var3.getAbsolutePath());
         var0.write(var5);
         var0.closeEntry();
      } else if (var3.isDirectory()) {
         for (String var7 : safeList(var3)) {
            compressFolderRecurse(var0, var1, new File(var2, var7).getPath());
         }
      }
   }

   public static void extractToFolder(File var0, File var1) throws IOException {
      try (ZipFile var2 = new ZipFile(var0)) {
         Enumeration var3 = var2.entries();

         while (var3.hasMoreElements()) {
            ZipEntry var4 = (ZipEntry)var3.nextElement();

            try (InputStream var5 = var2.getInputStream(var4)) {
               byte[] var6 = readInputStream(var5);
               File var7 = new File(var1, var4.getName());
               writeFile(var7, var6, true);
            }
         }
      }
   }

   public static void addFileToZip(ZipOutputStream var0, File var1, String var2) throws IOException {
      try (BufferedInputStream var3 = new BufferedInputStream(new FileInputStream(var1))) {
         ZipEntry var4 = new ZipEntry(var2);
         var4.setTime(var1.lastModified());
         var0.putNextEntry(var4);
         copyStream(var3, var0);
         var0.closeEntry();
      }
   }

   public static void addFileToJar(JarOutputStream var0, File var1, String var2) throws IOException {
      try (BufferedInputStream var3 = new BufferedInputStream(new FileInputStream(var1))) {
         JarEntry var4 = new JarEntry(var2);
         var4.setTime(var1.lastModified());
         var0.putNextEntry(var4);
         copyStream(var3, var0);
         var0.closeEntry();
      }
   }

   public static byte[] readFileSafe(File var0) {
      try {
         byte[] var5;
         try (DataInputStream var1 = new DataInputStream(new FileInputStream(var0))) {
            long var2 = var0.length();
            if (var2 > 2147483647L) {
               throw new IOException(Strings.ff("This method cannot read files larger than 2Gb: size=%d", var2));
            }

            byte[] var4 = new byte[(int)var2];
            var1.readFully(var4);
            var5 = var4;
         }

         return var5;
      } catch (IOException var8) {
         logger.catchingSilent(var8);
         return new byte[0];
      }
   }

   public static boolean writeFileSafe(File var0, byte[] var1, int var2, int var3, boolean var4) {
      try {
         writeFile(var0, var1, var2, var3, var4);
         return true;
      } catch (IOException var6) {
         logger.catchingSilent(var6);
         return false;
      }
   }

   public static boolean writeFileSafe(File var0, byte[] var1, boolean var2) {
      try {
         writeFile(var0, var1, 0, var1.length, var2);
         return true;
      } catch (IOException var4) {
         logger.catchingSilent(var4);
         return false;
      }
   }

   public static String readInputLineSafe() {
      BufferedReader var0 = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset()));

      try {
         return var0.readLine();
      } catch (IOException var2) {
         logger.catchingSilent(var2);
         return null;
      }
   }

   public static String readInputLine() throws IOException {
      BufferedReader var0 = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset()));
      return var0.readLine();
   }

   public static File getParentFile2(File var0) {
      File var1 = var0.getParentFile();
      if (var1 == null) {
         var1 = var0.getAbsoluteFile().getParentFile();
      }

      return var1;
   }

   public static String expandPath(String var0) {
      String var1 = System.getProperty("user.home");
      return var1 == null ? var0 : var0.replaceFirst("^~", Matcher.quoteReplacement(var1));
   }

   public static Thread copyAsync(InputStream var0, OutputStream var1) {
      return ThreadUtil.start("Async-Stream-Copy", new IO$3(var0, var1));
   }

   public static String dirname(String var0) {
      int var1 = Strings.lastIndexOf2(var0, '/', '\\');
      if (var1 < 0) {
         return "";
      } else {
         var1--;

         while (var1 >= 0) {
            char var2 = var0.charAt(var1);
            if (var2 != '/' && var2 != '\\') {
               break;
            }

            var1--;
         }

         return var1 < 0 ? "" : var0.substring(0, var1 + 1);
      }
   }

   public static String basename(String var0) {
      int var1 = Strings.lastIndexOf2(var0, '/', '\\');
      return var1 < 0 ? var0 : var0.substring(var1 + 1);
   }

   public static List parsePathElements(String var0) {
      ArrayList var1 = new ArrayList();

      for (String var5 : var0.split("(/|\\\\)")) {
         if (!var5.isEmpty()) {
            var1.add(var5);
         }
      }

      return var1;
   }

   public static String escapeFileName(String var0) {
      return escapeFileName(var0, '_');
   }

   public static String escapeFileName(String var0, char var1) {
      return var0.replaceAll("[:\\\\/*\"?|<>]", var1 + "");
   }

   public static String escapeFileNameStrict(String var0) {
      return escapeFileNameStrict(var0, '_');
   }

   public static String escapeFileNameStrict(String var0, char var1) {
      return var0.replaceAll("[^a-zA-Z0-9-_\\.']", var1 + "");
   }

   public static String sanitizePathUnsafe(String var0) {
      return sanitizePath(var0, false, false);
   }

   public static String sanitizePath(String var0, boolean var1, boolean var2) {
      if (IS_WINDOWS) {
         StringBuilder var3 = new StringBuilder();
         int var4 = -1;

         for (int var5 = 0; var5 < var0.length(); var5++) {
            char var6 = var0.charAt(var5);
            if (var6 != '/' && var6 != '\\') {
               if (var4 == -1) {
                  var4 = var5;
               }
            } else {
               if (var4 != -1) {
                  var3.append(sanitizeReservedWindowsFileName(var0.substring(var4, var5)));
                  var4 = -1;
               }

               var3.append(var6);
            }
         }

         if (var4 != -1) {
            var3.append(sanitizeReservedWindowsFileName(var0.substring(var4, var0.length())));
         }

         var0 = var3.toString();
      }

      int var12 = -1;

      while (true) {
         try {
            Paths.get(var0);
            StringBuilder var14 = new StringBuilder();
            int var17 = 0;

            for (int var20 = 0; var20 < var0.length(); var20++) {
               char var7 = var0.charAt(var20);
               if (!var1) {
                  boolean var8 = var7 == '/' || var7 == '\\' || var7 == ':';
                  if (var14.length() - var17 > 248 && var8) {
                     var14.delete(var17 + 248 - "~1".length(), var14.length());
                     var14.append("~1");
                  }

                  if (var8) {
                     var17 = var14.length() + 1;
                  }
               }

               if (var7 != '?'
                  && var7 != '%'
                  && var7 != '*'
                  && var7 != '|'
                  && var7 != '<'
                  && var7 != '>'
                  && var7 != '"'
                  && (!var1 || var7 != '/' && var7 != '\\' && var7 != ':')
                  && (!var2 || !Character.isWhitespace(var7))) {
                  var14.append(var7);
               } else {
                  Strings.ff(var14, "_x%04x", var7 & '\uffff');
               }
            }

            if (var14.length() - var17 > 255) {
               int var21 = var14.lastIndexOf(".");
               if (var21 >= 0 && var21 + 50 >= var14.length()) {
                  CharSequence var22 = var14.subSequence(var21, var14.length());
                  var14.delete(var17 + 255 - "~1".length() - var22.length(), var14.length());
                  var14.append("~1");
                  var14.append(var22);
               } else {
                  var14.delete(var17 + 255 - "~1".length(), var14.length());
                  var14.append("~1");
               }
            }

            return var14.toString();
         } catch (InvalidPathException var9) {
            int var15 = var9.getIndex();
            if (var15 <= var12) {
               StringBuilder var13 = new StringBuilder();

               for (int var16 = 0; var16 < var0.length(); var16++) {
                  char var19 = var0.charAt(var16);
                  if ((var19 < 'a' || var19 > 'z')
                     && (var19 < 'A' || var19 > 'Z')
                     && (var19 < '0' || var19 > '9')
                     && var19 != '-'
                     && var19 != '_'
                     && var19 != '.') {
                     Strings.ff(var13, "_x%04x", var19 & '\uffff');
                  } else {
                     var13.append(var19);
                  }
               }

               var0 = var13.toString();
               Paths.get(var0);
               return var0;
            }

            var12 = var15;
            var0 = var9.getInput();
            char var18 = var0.charAt(var15);
            var0 = var0.substring(0, var15) + Strings.ff("_x%04x", var18 & '\uffff') + var0.substring(var15 + 1);
         }
      }
   }

   private static String sanitizeReservedWindowsFileName(String var0) {
      String var1 = var0.toLowerCase();
      int var2 = var1.indexOf(46);
      if (var2 >= 0) {
         var1 = var1.substring(0, var2);
      }

      if (windowsIllegalFileNames.contains(var1)) {
         if (var2 < 0) {
            var0 = var0 + " ";
         } else {
            var0 = var0.substring(0, var2) + " " + var0.substring(var2);
         }
      }

      return var0;
   }

   public static String getRelativePath(File var0, File var1) {
      String var2 = abs(var0.getPath());
      String var3 = abs(var1.getPath());
      if (!var2.startsWith(var3)) {
         return null;
      } else {
         String var4 = var2.substring(var3.length());
         var4 = Strings.ltrim(var4, '/');
         return Strings.ltrim(var4, '\\');
      }
   }

   public static boolean compareFiles(File var0, File var1) throws IOException {
      if (!var0.isFile()) {
         throw new FileNotFoundException("File not found: " + var0);
      } else if (!var1.isFile()) {
         throw new FileNotFoundException("File not found: " + var1);
      } else if (var0.length() != var1.length()) {
         return false;
      } else {
         boolean var4;
         try (
            BufferedInputStream var2 = new BufferedInputStream(new FileInputStream(var0));
            BufferedInputStream var3 = new BufferedInputStream(new FileInputStream(var1));
         ) {
            var4 = compareStreams(var2, var3);
         }

         return var4;
      }
   }

   private static boolean compareStreams(InputStream var0, InputStream var1) throws IOException {
      int var2;
      int var3;
      do {
         var2 = var0.read();
         var3 = var1.read();
         if (var2 == -1 && var3 == -1) {
            return true;
         }
      } while (var2 == var3);

      return false;
   }

   public static String[] splitPath(String var0) {
      int var1 = Strings.lastIndexOf2(var0, '/', '\\');
      return var1 < 0 ? new String[]{"", var0} : new String[]{var0.substring(0, var1 + 1), var0.substring(var1 + 1)};
   }

   public static String[] splitExtension(String var0) {
      int var1 = Strings.lastIndexOf2(var0, '/', '\\');
      int var2 = var0.lastIndexOf(46);
      return var2 <= var1 ? new String[]{var0, ""} : new String[]{var0.substring(0, var2), var0.substring(var2)};
   }

   public static String getExtension(String var0) {
      int var1 = Strings.lastIndexOf2(var0, '/', '\\');
      int var2 = var0.lastIndexOf(46);
      return var2 <= var1 ? "" : var0.substring(var2);
   }

   public static String getExtension(File var0) {
      return getExtension(var0.getAbsolutePath());
   }

   public static String noExtension(String var0) {
      int var1 = Strings.lastIndexOf2(var0, '/', '\\');
      int var2 = var0.lastIndexOf(46);
      return var2 <= var1 ? var0 : var0.substring(0, var2);
   }

   public static File noExtension(File var0) {
      return new File(noExtension(var0.getAbsolutePath()));
   }

   public static File replaceExtension(File var0, String var1) {
      return new File(noExtension(var0.getAbsolutePath()) + var1);
   }

   public static boolean isUrl(String var0) {
      try {
         URI var1 = URI.create(var0);
         return var1.getScheme() != null;
      } catch (Exception var2) {
         return false;
      }
   }

   public static String abs(File var0) {
      return abs(var0.getPath());
   }

   public static String abs(String var0) {
      return simplifyPath(new File(var0).getAbsolutePath());
   }

   public static String simplifyPath(String var0) {
      if (File.separatorChar == '/') {
         return simplifyPathUnix(var0);
      } else {
         return File.separatorChar == '\\' ? simplifyPathWindows(var0) : var0;
      }
   }

   public static String simplifyPathUnix(String var0) {
      if (var0.isEmpty()) {
         return ".";
      } else {
         char var1 = var0.charAt(0);
         boolean var2 = false;
         if (var1 == '/') {
            var2 = true;
         }

         String[] var3 = var0.split("/");
         ArrayList var4 = new ArrayList(var3.length);

         for (int var5 = 0; var5 < var3.length; var5++) {
            String var6 = var3[var5];
            if (!var6.isEmpty() && !var6.equals(".")) {
               if (var6.equals("..")) {
                  if (!var4.isEmpty()) {
                     var4.remove(var4.size() - 1);
                  }
               } else {
                  var4.add(var6);
               }
            }
         }

         if (!var4.isEmpty()) {
            String var7 = Strings.join("/", var4);
            if (var2) {
               var7 = "/" + var7;
            }

            return var7;
         } else {
            return var2 ? "/" : ".";
         }
      }
   }

   public static String simplifyPathWindows(String var0) {
      if (var0.isEmpty()) {
         return ".";
      } else {
         var0 = var0.replace('/', '\\');
         char var1 = var0.charAt(0);
         byte var2 = 0;
         if (var1 == '\\') {
            if (var0.length() >= 2 && var0.charAt(1) == '\\') {
               if (var0.length() >= 3 && var0.charAt(2) == '\\') {
                  return var0;
               }

               var2 = 2;
            } else {
               var2 = 1;
            }
         }

         String[] var3 = var0.split("\\\\");
         ArrayList var4 = new ArrayList(var3.length);

         for (int var5 = 0; var5 < var3.length; var5++) {
            String var6 = var3[var5];
            if (!var6.isEmpty() && !var6.equals(".")) {
               if (var6.equals("..")) {
                  if (!var4.isEmpty()) {
                     var4.remove(var4.size() - 1);
                  }
               } else {
                  var4.add(var6);
               }
            }
         }

         if (!var4.isEmpty()) {
            String var8 = Strings.join("\\", var4);
            if (var2 == 1) {
               var8 = "\\" + var8;
            } else if (var2 == 2) {
               var8 = "\\\\" + var8;
            }

            return var8;
         } else if (var2 == 1) {
            return "\\";
         } else {
            return var2 == 2 ? "\\\\" : ".";
         }
      }
   }

   public static boolean checkFileFreshness(File var0, long var1, boolean var3) {
      if (!var0.exists()) {
         return false;
      } else if (System.currentTimeMillis() - var0.lastModified() <= var1) {
         return true;
      } else {
         if (var3) {
            var0.delete();
         }

         return false;
      }
   }

   static boolean mkdirs2Ex(File var0) throws IOException {
      if (var0 == null) {
         throw new IllegalArgumentException("Null file");
      } else if (var0.isDirectory()) {
         return false;
      } else {
         mkdirs2(var0);
         return true;
      }
   }

   static void mkdirs2(File var0) throws IOException {
      if (var0 == null) {
         throw new IllegalArgumentException("Null file");
      } else if (disablingMkdirsReplacement) {
         var0.mkdirs();
      } else if (!mkdirs(var0)) {
         if (var0.exists()) {
            if (var0.isDirectory()) {
               throw new IOException(Strings.ff("Failed to create %s: directory exists", var0));
            } else {
               throw new IOException(Strings.ff("Failed to create %s: entry exists and is not a directory", var0));
            }
         } else {
            File var1;
            try {
               var1 = var0.getCanonicalFile();
            } catch (IOException var4) {
               throw new IOException(Strings.ff("Failed to create %s: cannot canonicalize path", var0), var4);
            }

            try {
               mkdirsExSafeRec(var1);
            } catch (IOException var3) {
               throw new IOException(Strings.ff("Failed to create %s (canon: %s): an exception occurred", var0, var1), var3);
            }
         }
      }
   }

   private static void mkdirsExSafeRec(File var0) throws IOException {
      File var1 = var0.getParentFile();
      if (var1 == null) {
         throw new IOException("No more parents");
      } else {
         if (!var1.exists()) {
            mkdirsExSafeRec(var1);
         }

         if (!mkdirWarn(var0)) {
            throw new IOException("Cannot create intermediate folder: " + var0);
         }
      }
   }

   private static boolean mkdirs(File var0) {
      if (var0.exists()) {
         return false;
      } else if (mkdirWarn(var0)) {
         return true;
      } else {
         File var1;
         try {
            var1 = var0.getCanonicalFile();
         } catch (IOException var3) {
            logger.catching(var3);
            return false;
         }

         File var2 = var1.getParentFile();
         return var2 != null && (mkdirs(var2) || var2.exists()) && mkdirWarn(var1);
      }
   }

   private static boolean mkdirWarn(File var0) {
      if (var0.getPath().endsWith(" ")) {
         logger.warn(S.L("Warning: Folder name ends with space, it may not be created as expected: '%s'"), var0);
      }

      return var0.mkdir();
   }

   static {
      File var0 = null;

      try {
         var0 = File.createTempFile("jebgettemp", null);
         tempFolder = var0.getParentFile();
         safeTempFolder = new File(tempFolder, "jebtmp-" + System.currentTimeMillis());
         mkdirWarn(safeTempFolder);
         Runtime.getRuntime().addShutdownHook(new IO$1());
      } catch (IOException var5) {
         throw new RuntimeException(var5);
      } finally {
         if (var0 != null) {
            var0.delete();
         }
      }

      windowsIllegalFileNames = new HashSet();
      windowsIllegalFileNames.add("con");
      windowsIllegalFileNames.add("prn");
      windowsIllegalFileNames.add("aux");
      windowsIllegalFileNames.add("nul");

      for (int var7 = 1; var7 <= 9; var7++) {
         windowsIllegalFileNames.add("com" + var7);
         windowsIllegalFileNames.add("lpt" + var7);
      }

      disablingMkdirsReplacement = true;
   }
}
