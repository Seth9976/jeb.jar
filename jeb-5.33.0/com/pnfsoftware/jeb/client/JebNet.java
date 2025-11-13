package com.pnfsoftware.jeb.client;

import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.io.LEDataOutputStream;
import com.pnfsoftware.jeb.util.io.StreamWrappers;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.net.FormFileEntry;
import com.pnfsoftware.jeb.util.net.INet;
import com.pnfsoftware.jeb.util.net.Net;
import com.pnfsoftware.jebglobal.ckx;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.zip.GZIPOutputStream;

public class JebNet {
   private static final ILogger logger = GlobalLog.getLogger(JebNet.class);

   public static String post(INet var0, String var1, String var2) {
      return post(var0, var1, var2, null);
   }

   public static String post(INet var0, String var1, String var2, long[] var3) {
      HashMap var4 = new HashMap();
      var4.put("data", var2);
      HashMap var5 = new HashMap();
      var5.put("Date", null);

      String var6;
      try {
         var6 = var0.post(var1, null, var4, var5);
      } catch (IOException var7) {
         return null;
      }

      if (var3 != null && var3.length >= 1) {
         var3[0] = Net.convertHttpDateToEpoch((String)var5.get("Date"));
      }

      return var6;
   }

   public static long getSupposedlyGoodEpoch(INet var0) {
      String var1 = ckx.pC(new byte[]{95, 28, 0, 4, 3, 73, 21, 0, 88, 0, 0, 89, 89, 68, 29, 65, 29, 21}, 1, 55);
      HashMap var2 = new HashMap();
      var2.put("Date", null);

      try {
         var0.query(var1, null, var2);
      } catch (IOException var3) {
         return 0L;
      }

      return Net.convertHttpDateToEpoch((String)var2.get("Date"));
   }

   public static String uploadFile(INet var0, File var1, boolean var2) throws IOException {
      return uploadFile(var0, var1, var2, null);
   }

   public static String uploadFile(INet var0, File var1, boolean var2, String var3) throws IOException {
      var0 = var0.duplicate();
      var0.setWriteTimeout(600000);
      String var4 = var1.getName();
      if (var2) {
         var1 = protectFile(var1);
      }

      HashMap var5 = new HashMap();
      var5.put("pfile", new FormFileEntry(var1, var4));
      HashMap var6 = new HashMap();
      var6.put("comments", "bugreport");
      if (var3 == null) {
         var3 = "https://www.pnfsoftware.com/u";
      }

      return var0.postMultipart(var3, var6, var5);
   }

   private static File protectFile(File var0) throws IOException {
      byte[] var1 = new byte[]{4, 8, 15, 16, 23, 42};
      boolean var2 = true;
      IO.getFirstShortLE(var0.getAbsolutePath());
      boolean var3 = false;
      byte var4 = 0;
      if (var2) {
         var4 |= 1;
      }

      if (var3) {
         var4 |= 2;
      }

      File var5 = IO.createTempFile();
      var5.deleteOnExit();

      try (LEDataOutputStream var6 = new LEDataOutputStream(new FileOutputStream(var5))) {
         var6.writeByte(74);
         var6.writeByte(85);
         var6.writeByte(1);
         var6.writeByte((byte)var4);
         var6.writeInt((int)var0.length());
         var6.writeInt(0);
         var6.writeInt(0);
      }

      File var8;
      try (FileInputStream var18 = new FileInputStream(var0)) {
         if (var3) {
            try (FilterOutputStream var19 = StreamWrappers.getEncryptedStream(new GZIPOutputStream(new FileOutputStream(var5, true)), "RC4", var1)) {
               IO.copy(var18, var19);
               return var5;
            }
         }

         try (FilterOutputStream var7 = StreamWrappers.getEncryptedStream(new FileOutputStream(var5, true), "RC4", var1)) {
            IO.copy(var18, var7);
            var8 = var5;
         }
      }

      return var8;
   }
}
