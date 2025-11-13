package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.explorer;

import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.output.text.ITextDocument;
import com.pnfsoftware.jeb.core.output.text.TextDocumentUtil;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledItem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeSourceUnit;
import com.pnfsoftware.jeb.core.util.DecompilerHelper;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.DirectByteArrayOutputStream;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.Deserializer;
import com.pnfsoftware.jeb.util.serialization.SerializationManager;
import com.pnfsoftware.jeb.util.serialization.Serializer;
import com.pnfsoftware.jebglobal.abg;
import com.pnfsoftware.jebglobal.axp;
import com.pnfsoftware.jebglobal.baj;
import com.pnfsoftware.jebglobal.bak;
import com.pnfsoftware.jebglobal.cuu;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DecompileAll {
   private static final ILogger q = GlobalLog.getLogger(DecompileAll.class);
   private static final String RF = "--dump-all";
   private static final String xK = "--dump-plaintext-ast";
   private static final String Dw = "--dump-ser-ast";
   private static final String Uv = "--max-routines";
   private static final String oW = "--max-routine-size";
   private static final String gO = "--unique-hash";
   private static final String nf = "--unique-hash-db";
   private static String gP = null;
   private static int za = 0;
   private static int lm = 0;
   private static boolean zz;
   private static String JY = null;
   private static Set HF;
   private static bak LK;

   public static void main(String[] var0) throws JebException, IOException {
      DecompileAll.eo var1 = q(var0);
      gP = xK();
      int var2 = q(var0[0], var0[1], var1);
      if (JY != null) {
         RF();
      }

      Object[] var10000 = new Object[]{var2};
      System.exit(var2);
   }

   private static DecompileAll.eo q(String[] var0) {
      String var1 = Strings.ff(
         "Usage: <executable_file_path> <output_folder_path> %s|%s|%s [%s] [%s=int] [%s=int] [%s=path]",
         "--dump-all",
         "--dump-plaintext-ast",
         "--dump-ser-ast",
         "--unique-hash",
         "--max-routine-size",
         "--max-routines",
         "--unique-hash-db"
      );
      Assert.a(var0.length >= 3, var1);
      Assert.a(new File(var0[0]).isFile(), var1);
      Assert.a(new File(var0[1]).isDirectory(), var1);
      DecompileAll.eo var2 = q(var0[2]);
      Assert.a(var2 != null, var1);

      for (int var3 = 3; var3 < var0.length; var3++) {
         if (var0[var3].startsWith("--max-routine-size")) {
            za = Integer.parseInt(var0[var3].substring("--max-routine-size".length() + 1));
         }

         if (var0[var3].startsWith("--max-routines")) {
            lm = Integer.parseInt(var0[var3].substring("--max-routines".length() + 1));
         }

         if (var0[var3].equals("--unique-hash")) {
            zz = true;
         }

         if (var0[var3].startsWith("--unique-hash-db")) {
            JY = var0[var3].substring("--unique-hash-db".length() + 1);
            q();
         }
      }

      return var2;
   }

   private static void q() {
      File var0 = new File(JY);
      if (var0.exists()) {
         try (FileInputStream var1 = new FileInputStream(var0)) {
            cuu var2 = cuu.q();
            SerializationManager var3 = new SerializationManager(var2);
            Deserializer var4 = var3.getDeserializer(var1);
            HF = (Set)var4.deserialize();
         } catch (IOException var7) {
            q.error("File %s is not a valid unique hash set", var0);
         }
      }
   }

   private static void RF() {
      q(new File(JY), HF);
   }

   private static DecompileAll.eo q(String var0) {
      switch (var0) {
         case "--dump-all":
            return DecompileAll.eo.q;
         case "--dump-plaintext-ast":
            return DecompileAll.eo.RF;
         case "--dump-ser-ast":
            return DecompileAll.eo.xK;
         default:
            return null;
      }
   }

   private static String xK() {
      String var0 = null;

      try {
         Process var1 = Runtime.getRuntime().exec("git log -1 --format=%ct_%h");
         var1.waitFor();
         BufferedReader var2 = new BufferedReader(new InputStreamReader(var1.getInputStream(), Charset.defaultCharset()));
         var0 = var2.readLine();
      } catch (Exception var3) {
         q.catching(var3);
      }

      return var0;
   }

   private static int q(String var0, String var1, DecompileAll.eo var2) throws JebException, IOException {
      abg var3 = RF(var0);
      if (var3 == null) {
         q.error("cant get the native code unit");
         return 0;
      } else {
         INativeDecompilerUnit var4 = (INativeDecompilerUnit)DecompilerHelper.getDecompiler(var3);
         IPropertyManager var5 = var4.getPropertyManager();
         var5.setBoolean("UseFriendlyVariableNames", true);
         var5.setBoolean("MergeAdjacentDefinitions", true);
         List var6 = var3.getInternalMethodsLeafFirst();
         int var7 = 0;
         int var8 = 0;

         for (axp var10 : var6) {
            Object[] var10000 = new Object[]{++var7, var6.size(), var10.getName(true), var10.oW().getCFG().getEffectiveSize()};
            if (zz && q(var10, var3)) {
               var10000 = new Object[0];
            } else if (za != 0 && var10.oW().getCFG().getEffectiveSize() >= za) {
               q.error("too large: not decompiled");
            } else {
               if (lm != 0 && var8 >= lm) {
                  var10000 = new Object[0];
                  break;
               }

               INativeSourceUnit var11 = q(var10, var4);
               if (var11 == null) {
                  q.error("decompilation failed");
               } else {
                  var8++;
                  q(var11, var1, var2);
               }
            }
         }

         return var8;
      }
   }

   private static void q(INativeSourceUnit var0, String var1, DecompileAll.eo var2) {
      File var3 = new File(var1 + File.separator + gP);
      if (!var3.exists()) {
         var3.mkdir();
      }

      String var4 = null;
      IDecompiledItem var5 = var0.getDecompiledItem();
      if (var5 instanceof IDecompiledMethod) {
         var4 = ((IDecompiledMethod)var5).getMethodItem().getName();
      }

      if (var2.q()) {
         q(new File(var3, var4 + ".c"), var0);
      }

      if (var2.RF()) {
         RF(new File(var3, var4 + ".ast.ser"), var0);
      }
   }

   private static void q(File var0, INativeSourceUnit var1) {
      ITextDocument var2 = (ITextDocument)var1.getFormatter().getPresentation(0).getDocument();
      StringBuilder var3 = TextDocumentUtil.buildText(var2);

      try {
         IO.writeFile(var0, var3.toString());
      } catch (IOException var4) {
         q.error("error when writing file");
      }
   }

   private static void RF(File var0, INativeSourceUnit var1) {
      q(var0, var1.getASTItem());
   }

   private static void q(File var0, Object var1) {
      try (DirectByteArrayOutputStream var2 = new DirectByteArrayOutputStream()) {
         cuu var3 = cuu.q();
         SerializationManager var4 = new SerializationManager(var3);
         Serializer var5 = var4.getSerializer(var2);
         var5.serialize(var1);
         var5.close();
         IO.writeFile(var0, var2.getRawBytes(), 0, var2.size());
      } catch (IOException var8) {
         q.error("ser error");
      }
   }

   private static abg RF(String var0) throws JebException, IOException {
      qV var1 = new qV(new File(var0).getParent(), new File(var0).getName());
      List var2 = var1.q();
      if (var2 != null && !var2.isEmpty()) {
         abg var3 = (abg)UnitUtil.findChildByType((IUnit)var2.get(0), abg.class, false, 0);
         if (var3 == null) {
            q.error("cannot find PBCU");
            return null;
         } else if (!var3.process()) {
            q.error("unit processing failed");
            return null;
         } else {
            return var3;
         }
      } else {
         q.error("problem with root unit creation");
         return null;
      }
   }

   private static INativeSourceUnit q(axp var0, INativeDecompilerUnit var1) {
      INativeSourceUnit var2 = null;

      try {
         GlobalLog.addGlobalFilter("", Integer.MAX_VALUE);
         var2 = var1.decompileToUnit(var0.getAddress());
         GlobalLog.removeGlobalFilter("");
      } catch (Exception var4) {
         GlobalLog.removeGlobalFilter("");
         q.error("FAILED: %s: %s", var4.getClass().getName(), var4.getMessage());
         q.catching(var4);
      }

      return var2;
   }

   private static boolean q(axp var0, abg var1) {
      if (HF == null) {
         HF = new HashSet();
      }

      if (LK == null) {
         LK = new bak(var1.getProcessor());
      }

      LK.q(var0);
      baj var2 = (baj)LK.q().get(0);
      LK.RF();
      return !HF.add(var2);
   }

   static {
      GlobalLog.addDestinationStream(System.out);
   }

   static enum eo {
      q,
      RF,
      xK;

      boolean q() {
         return this == RF || this == q;
      }

      boolean RF() {
         return this == xK || this == q;
      }
   }
}
