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
import com.pnfsoftware.jebglobal.bby;
import com.pnfsoftware.jebglobal.bca;
import com.pnfsoftware.jebglobal.bcb;
import com.pnfsoftware.jebglobal.bci;
import com.pnfsoftware.jebglobal.bcj;
import com.pnfsoftware.jebglobal.bck;
import com.pnfsoftware.jebglobal.bcn;
import com.pnfsoftware.jebglobal.bcp;
import com.pnfsoftware.jebglobal.cuu;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class TLGen {
   private static final ILogger Dw;
   bby q;
   File RF;
   File xK;

   public static void main(String[] var0) throws Exception {
      if (var0.length == 0) {
         String var12 = "TLGEN - JEB Type Library Generator -- Nicolas Falliere (c) PNF Software\nUsage:\n    TLGEN somelib.tld.yml [anotherlib.tld.yml [...]]\n    TLGEN -f list.txt\n";
         Dw.warn(var12);
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

            var13.addAll(eo.q(new File(var6)));
         }

         if (var3 != null) {
            for (String var9 : Strings.splitLines(Strings.decodeLocal(IO.readFile(var3)))) {
               String var10 = var9.trim();
               if (!Strings.isBlank(var10) && !var10.startsWith("#")) {
                  File var11 = new File(var10);
                  if (!var11.isAbsolute()) {
                     var11 = new File(var3.getParentFile(), var10);
                  }

                  var13.addAll(eo.q(var11));
               }
            }
         }

         Object[] var10000 = new Object[]{var13};
         int var15 = q(var13, var1, var2, -1);
         int var16 = q(var13, var1, var2, var15);
         Dw.info("=> %d/%d typelibs were successfully built", var16, var15);
         if (var16 != var15) {
            Dw.error("+++ THERE WERE ERRORS! +++");
         }
      }
   }

   private static int q(List var0, boolean var1, List var2, int var3) {
      boolean var4 = var3 < 0;
      int var5 = 0;
      int var6 = 0;

      for (eo var8 : var0) {
         if (var1 || var2.contains(var8.Uv.getGroupId()) || var8.q) {
            if (!var4) {
               Dw.info("+++ %d/%d (success: %d/%d) +++ Building typelib: %s", var6 + 1, var3, var5, var6, var8.Uv.getName());
               boolean var9 = q(var8);
               Dw.info("Done building typelib: %s (%s)", var8.Uv.getName(), var9 ? "SUCCESS" : "FAILURE");
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

   private static boolean q(eo var0) {
      bby var1 = bby.q(var0.Uv.getPrimaryProcessorType(), var0.Uv.getPrimarySubsystemType(), var0.Uv.getCompilerType());

      for (Entry var3 : var0.gO.entrySet()) {
         var1.xK().q((String)var3.getKey(), (Integer)((Couple)var3.getValue()).getFirst(), (PrimitiveCategory)((Couple)var3.getValue()).getSecond());
      }

      TLGen var5 = new TLGen(var1, var0.RF, var0.xK);

      try {
         var5.q(var0.Uv, true, var0.Dw);
         if (var0.oW != null) {
            IO.copyFile(var0.Dw, var0.oW, true);
         }

         return true;
      } catch (Exception var4) {
         Dw.catching(var4);
         return false;
      }
   }

   public TLGen(bby var1, File var2, File var3) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
   }

   public bck q(TypeLibraryMetadata var1, boolean var2, File var3) throws IOException {
      bck var4 = new bck(var1, this.q);
      bca var5 = new bca(var1.getStandardPackingAlignment(), var2);
      if (this.RF != null) {
         bcn var6 = new bcn(CharStreams.fromFileName(this.RF.getPath()));
         CommonTokenStream var7 = new CommonTokenStream(var6);
         bcp var8 = new bcp(var7);
         bcp.PY var9 = var8.Jf();
         bcj var10 = new bcj(var9, var5, this.q);
         var10.Dw();
         bci var11 = new bci(var10);
         var11.q(var4);
      }

      if (this.xK != null) {
         bcb var12 = new bcb(this.xK);
         var12.q(var4);
      }

      if (var3 != null) {
         cuu var13 = cuu.q();
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

      Dw = GlobalLog.getLogger(TLGen.class, 30);
   }
}
