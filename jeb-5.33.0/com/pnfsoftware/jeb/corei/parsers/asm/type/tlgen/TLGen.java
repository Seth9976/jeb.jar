package com.pnfsoftware.jeb.corei.parsers.asm.type.tlgen;

import com.pnfsoftware.jeb.core.units.code.asm.type.PrimitiveCategory;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeLibraryMetadata;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.DirectByteArrayOutputStream;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.SerializationManager;
import com.pnfsoftware.jeb.util.serialization.Serializer;
import com.pnfsoftware.jebglobal.ayy;
import com.pnfsoftware.jebglobal.aza;
import com.pnfsoftware.jebglobal.azb;
import com.pnfsoftware.jebglobal.azi;
import com.pnfsoftware.jebglobal.azj;
import com.pnfsoftware.jebglobal.azk;
import com.pnfsoftware.jebglobal.azl;
import com.pnfsoftware.jebglobal.azn;
import com.pnfsoftware.jebglobal.ckh;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class TLGen {
   private static final ILogger wS;
   ayy pC;
   File A;
   File kS;

   public static void main(String[] var0) throws Exception {
      if (var0.length == 0) {
         String var12 = "TLGEN - JEB Type Library Generator -- Nicolas Falliere (c) PNF Software\nUsage:\n    TLGEN somelib.tld.yml [anotherlib.tld.yml [...]]\n    TLGEN -f list.txt\n";
         wS.warn(var12);
      } else {
         boolean var1 = false;
         ArrayList var2 = new ArrayList();
         File var3 = null;

         int var4;
         for (var4 = 0; var4 < var0.length; var4++) {
            String var5 = var0[var4];
            if (!var5.startsWith("-")) {
               break;
            }

            if (var5.equals("-f")) {
               if (var4 + 1 >= var0.length) {
                  throw new IllegalArgumentException("Missing filepath after -f");
               }

               var3 = new File(var0[var4 + 1]);
               var4++;
            }
         }

         ArrayList var13;
         for (var13 = new ArrayList(); var4 < var0.length; var4++) {
            String var6 = var0[var4];
            if (var6.startsWith("#")) {
               break;
            }

            var13.addAll(Av.pC(new File(var6)));
         }

         if (var3 != null) {
            for (String var9 : Strings.splitLines(Strings.decodeLocal(IO.readFile(var3)))) {
               String var10 = var9.trim();
               if (!Strings.isBlank(var10) && !var10.startsWith("#")) {
                  File var11 = new File(var10);
                  if (!var11.isAbsolute()) {
                     var11 = new File(var3.getParentFile(), var10);
                  }

                  var13.addAll(Av.pC(var11));
               }
            }
         }

         Object[] var10000 = new Object[]{var13};
         int var15 = pC(var13, var1, var2, -1);
         int var16 = pC(var13, var1, var2, var15);
         wS.info("=> %d/%d typelibs were successfully built", var16, var15);
         if (var16 != var15) {
            wS.error("+++ THERE WERE ERRORS! +++");
         }
      }
   }

   private static int pC(List var0, boolean var1, List var2, int var3) {
      boolean var4 = var3 < 0;
      int var5 = 0;
      int var6 = 0;

      for (Av var8 : var0) {
         if (var1 || var2.contains(var8.UT.getGroupId()) || var8.pC) {
            if (!var4) {
               wS.info("+++ %d/%d (success: %d/%d) +++ Building typelib: %s", var6 + 1, var3, var5, var6, var8.UT.getName());
               boolean var9 = pC(var8);
               wS.info("Done building typelib: %s (%s)", var8.UT.getName(), var9 ? "SUCCESS" : "FAILURE");
               var6++;
               if (!var9) {
                  continue;
               }
            }

            var5++;
         }
      }

      return var5;
   }

   private static boolean pC(Av var0) {
      ayy var1 = ayy.pC(var0.UT.getPrimaryProcessorType(), var0.UT.getPrimarySubsystemType(), var0.UT.getCompilerType());

      for (Entry var3 : var0.sY.entrySet()) {
         var1.pC().pC((String)var3.getKey(), (Integer)((Couple)var3.getValue()).getFirst(), (PrimitiveCategory)((Couple)var3.getValue()).getSecond());
      }

      TLGen var5 = new TLGen(var1, var0.A, var0.kS);

      try {
         var5.pC(var0.UT, true, var0.wS);
         if (var0.E != null) {
            IO.copyFile(var0.wS, var0.E, true);
         }

         return true;
      } catch (Exception var4) {
         wS.catching(var4);
         return false;
      }
   }

   public TLGen(ayy var1, File var2, File var3) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
   }

   public azk pC(TypeLibraryMetadata var1, boolean var2, File var3) throws IOException {
      azk var4 = new azk(var1, this.pC);
      aza var5 = new aza(var1.getStandardPackingAlignment(), var2);
      if (this.A != null) {
         azl var6 = new azl(CharStreams.fromFileName(this.A.getPath()));
         CommonTokenStream var7 = new CommonTokenStream(var6);
         azn var8 = new azn(var7);
         azn.RC var9 = var8.ZD();
         azj var10 = new azj(var9, var5, this.pC);
         var10.wS();
         azi var11 = new azi(var10);
         var11.pC(var4);
      }

      if (this.kS != null) {
         azb var12 = new azb(this.kS);
         var12.pC(var4);
      }

      if (var3 != null) {
         ckh var13 = ckh.pC();
         SerializationManager var14 = new SerializationManager(var13);
         DirectByteArrayOutputStream var15 = new DirectByteArrayOutputStream();
         Serializer var16 = var14.getSerializer(var15);
         var16.serialize(var1);
         var16.serialize(var4);
         var16.close();
         IO.writeFile(var3, var15.getRawBytes(), 0, var15.size());
      }

      return var4;
   }

   static {
      GlobalLog.addDestinationStream(System.out);

      try {
         String var0 = System.getProperty("java.io.tmpdir");
         File var1 = new File(var0, "tlgen-output.log");
         GlobalLog.addDestinationStream(new PrintStream(var1));
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }

      wS = GlobalLog.getLogger(TLGen.class, 30);
   }
}
