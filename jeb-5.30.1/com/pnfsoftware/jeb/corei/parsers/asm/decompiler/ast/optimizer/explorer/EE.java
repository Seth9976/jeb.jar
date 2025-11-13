package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerEntry;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.DirectByteArrayOutputStream;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.Deserializer;
import com.pnfsoftware.jeb.util.serialization.SerializationManager;
import com.pnfsoftware.jeb.util.serialization.Serializer;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jebglobal.aeg;
import com.pnfsoftware.jebglobal.cuu;
import com.pnfsoftware.jebglobal.cvl;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Ser
public class EE {
   private static final StructuredLogger Dw = aeg.q(EE.class);
   @SerId(1)
   String q;
   @SerId(2)
   String RF;
   @SerId(3)
   List xK = new ArrayList();

   public EE(String var1, String var2) {
      this.q = var1;
      this.RF = var2;
   }

   public void q(OptimizerEntry var1, int var2, long var3) {
      EE.eo var5 = new EE.eo(var1, var2, var3);
      this.xK.add(var5);
   }

   public void q() {
      if (!this.xK.isEmpty()) {
         if (cvl.RF()) {
            File var1 = new File(Strings.ff("C:\\work\\PNF\\ast\\pipelinestats\\logger\\%s_%s_%d.log", this.q, this.RF, System.currentTimeMillis()));
            q(var1, this);
         } else {
            Dw.error("no output folder configured");
         }
      } else {
         Object[] var10000 = new Object[0];
      }
   }

   public static EE q(File var0) {
      try {
         EE var6;
         try (FileInputStream var1 = new FileInputStream(var0)) {
            cuu var2 = cuu.q();
            SerializationManager var3 = new SerializationManager(var2);
            Deserializer var4 = var3.getDeserializer(var1);
            EE var5 = (EE)var4.deserialize();
            var6 = var5;
         }

         return var6;
      } catch (IOException var9) {
         Dw.error("ser error");
         return null;
      }
   }

   private static void q(File var0, EE var1) {
      try (DirectByteArrayOutputStream var2 = new DirectByteArrayOutputStream()) {
         cuu var3 = cuu.q();
         SerializationManager var4 = new SerializationManager(var3);
         Serializer var5 = var4.getSerializer(var2);
         var5.serialize(var1);
         var5.close();
         IO.writeFile(var0, var2.getRawBytes(), 0, var2.size());
      } catch (IOException var8) {
         Dw.error("ser error");
      }
   }

   @Ser
   public static class eo {
      @SerId(1)
      int q;
      @SerId(2)
      String RF;
      @SerId(3)
      int xK;
      @SerId(4)
      long Dw;
      @SerTransient
      Bu.eo Uv;

      public eo(OptimizerEntry var1, int var2, long var3) {
         this.q = var1.getGroup();
         this.RF = var1.getOptimizer().getPluginInformation().getName();
         this.xK = var2;
         this.Dw = var3;
      }

      public boolean q() {
         return this.xK > 0;
      }

      public Bu.eo RF() {
         if (this.Uv == null) {
            this.Uv = new Bu.eo(this.q, this.RF);
         }

         return this.Uv;
      }
   }
}
