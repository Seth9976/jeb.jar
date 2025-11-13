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
import com.pnfsoftware.jebglobal.C;
import com.pnfsoftware.jebglobal.auu;
import com.pnfsoftware.jebglobal.axm;
import com.pnfsoftware.jebglobal.axn;
import com.pnfsoftware.jebglobal.ckh;
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
   private static final ILogger pC = GlobalLog.getLogger(DecompileAll.class);
   private static String A = null;
   private static int kS = 0;
   private static int wS = 0;
   private static boolean UT;
   private static String E = null;
   private static Set sY;
   private static axn ys;

   public static void main(String[] var0) throws JebException, IOException {
      DecompileAll.Av var1 = pC(var0);
      A = kS();
      int var2 = pC(var0[0], var0[1], var1);
      if (E != null) {
         A();
      }

      Object[] var10000 = new Object[]{var2};
      System.exit(var2);
   }

   private static DecompileAll.Av pC(String[] var0) {
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
      DecompileAll.Av var2 = pC(var0[2]);
      Assert.a(var2 != null, var1);

      for (int var3 = 3; var3 < var0.length; var3++) {
         if (var0[var3].startsWith("--max-routine-size")) {
            kS = Integer.parseInt(var0[var3].substring("--max-routine-size".length() + 1));
         }

         if (var0[var3].startsWith("--max-routines")) {
            wS = Integer.parseInt(var0[var3].substring("--max-routines".length() + 1));
         }

         if (var0[var3].equals("--unique-hash")) {
            UT = true;
         }

         if (var0[var3].startsWith("--unique-hash-db")) {
            E = var0[var3].substring("--unique-hash-db".length() + 1);
            pC();
         }
      }

      return var2;
   }

   private static void pC() {
      File var0 = new File(E);
      if (var0.exists()) {
         try (FileInputStream var1 = new FileInputStream(var0)) {
            ckh var2 = ckh.pC();
            SerializationManager var3 = new SerializationManager(var2);
            Deserializer var4 = var3.getDeserializer(var1);
            sY = (Set)var4.deserialize();
         } catch (IOException var7) {
            pC.error("File %s is not a valid unique hash set", var0);
         }
      }
   }

   private static void A() {
      pC(new File(E), sY);
   }

   private static DecompileAll.Av pC(String var0) {
      switch (var0) {
         case "--dump-all":
            return DecompileAll.Av.pC;
         case "--dump-plaintext-ast":
            return DecompileAll.Av.A;
         case "--dump-ser-ast":
            return DecompileAll.Av.kS;
         default:
            return null;
      }
   }

   private static String kS() {
      String var0 = null;

      try {
         Process var1 = Runtime.getRuntime().exec("git log -1 --format=%ct_%h");
         var1.waitFor();
         BufferedReader var2 = new BufferedReader(new InputStreamReader(var1.getInputStream(), Charset.defaultCharset()));
         var0 = var2.readLine();
      } catch (Exception var3) {
         pC.catching(var3);
      }

      return var0;
   }

   private static int pC(String var0, String var1, DecompileAll.Av var2) throws JebException, IOException {
      C var3 = A(var0);
      if (var3 == null) {
         pC.error("cant get the native code unit");
         return 0;
      } else {
         INativeDecompilerUnit var4 = (INativeDecompilerUnit)DecompilerHelper.getDecompiler(var3);
         IPropertyManager var5 = var4.getPropertyManager();
         var5.setBoolean("UseFriendlyVariableNames", true);
         var5.setBoolean("MergeAdjacentDefinitions", true);
         List var6 = var3.getInternalMethodsLeafFirst();
         int var7 = 0;
         int var8 = 0;

         for (auu var10 : var6) {
            Object[] var10000 = new Object[]{++var7, var6.size(), var10.getName(true), var10.E().getCFG().getEffectiveSize()};
            if (UT && pC(var10, var3)) {
               var10000 = new Object[0];
            } else if (kS != 0 && var10.E().getCFG().getEffectiveSize() >= kS) {
               pC.error("too large: not decompiled");
            } else {
               if (wS != 0 && var8 >= wS) {
                  var10000 = new Object[0];
                  break;
               }

               INativeSourceUnit var11 = pC(var10, var4);
               if (var11 == null) {
                  pC.error("decompilation failed");
               } else {
                  var8++;
                  pC(var11, var1, var2);
               }
            }
         }

         return var8;
      }
   }

   private static void pC(INativeSourceUnit var0, String var1, DecompileAll.Av var2) {
      File var3 = new File(var1 + File.separator + A);
      if (!var3.exists()) {
         var3.mkdir();
      }

      String var4 = null;
      IDecompiledItem var5 = var0.getDecompiledItem();
      if (var5 instanceof IDecompiledMethod) {
         var4 = ((IDecompiledMethod)var5).getMethodItem().getName();
      }

      if (var2.pC()) {
         pC(new File(var3, var4 + ".c"), var0);
      }

      if (var2.A()) {
         A(new File(var3, var4 + ".ast.ser"), var0);
      }
   }

   private static void pC(File var0, INativeSourceUnit var1) {
      ITextDocument var2 = (ITextDocument)var1.getFormatter().getPresentation(0).getDocument();
      StringBuilder var3 = TextDocumentUtil.buildText(var2);

      try {
         IO.writeFile(var0, var3.toString());
      } catch (IOException var4) {
         pC.error("error when writing file");
      }
   }

   private static void A(File var0, INativeSourceUnit var1) {
      pC(var0, var1.getASTItem());
   }

   private static void pC(File var0, Object var1) {
      try (DirectByteArrayOutputStream var2 = new DirectByteArrayOutputStream()) {
         ckh var3 = ckh.pC();
         SerializationManager var4 = new SerializationManager(var3);
         Serializer var5 = var4.getSerializer(var2);
         var5.serialize(var1);
         var5.close();
         IO.writeFile(var0, var2.getRawBytes(), 0, var2.size());
      } catch (IOException var8) {
         pC.error("ser error");
      }
   }

   private static C A(String var0) throws JebException, IOException {
      KD var1 = new KD(new File(var0).getParent(), new File(var0).getName());
      List var2 = var1.pC();
      if (var2 != null && !var2.isEmpty()) {
         C var3 = (C)UnitUtil.findChildByType((IUnit)var2.get(0), C.class, false, 0);
         if (var3 == null) {
            pC.error("cannot find PBCU");
            return null;
         } else if (!var3.process()) {
            pC.error("unit processing failed");
            return null;
         } else {
            return var3;
         }
      } else {
         pC.error("problem with root unit creation");
         return null;
      }
   }

   private static INativeSourceUnit pC(auu var0, INativeDecompilerUnit var1) {
      INativeSourceUnit var2 = null;

      try {
         GlobalLog.addGlobalFilter("", Integer.MAX_VALUE);
         var2 = var1.decompileToUnit(var0.getAddress());
         GlobalLog.removeGlobalFilter("");
      } catch (Exception var4) {
         GlobalLog.removeGlobalFilter("");
         pC.error("FAILED: %s: %s", var4.getClass().getName(), var4.getMessage());
         pC.catching(var4);
      }

      return var2;
   }

   private static boolean pC(auu var0, C var1) {
      if (sY == null) {
         sY = new HashSet();
      }

      if (ys == null) {
         ys = new axn(var1.getProcessor());
      }

      ys.pC(var0);
      axm var2 = (axm)ys.pC().get(0);
      ys.A();
      return !sY.add(var2);
   }

   static {
      GlobalLog.addDestinationStream(System.out);
   }

   static enum Av {
      pC,
      A,
      kS;

      boolean pC() {
         return this == A || this == pC;
      }

      boolean A() {
         return this == kS || this == pC;
      }
   }
}
