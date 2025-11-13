package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen;

import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeSignature;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.DirectByteArrayOutputStream;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.Deserializer;
import com.pnfsoftware.jeb.util.serialization.SerializationManager;
import com.pnfsoftware.jeb.util.serialization.Serializer;
import com.pnfsoftware.jebglobal.cuu;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class PY {
   private static final ILogger xK = GlobalLog.getLogger(PY.class);
   public static final String q = "removals.txt";
   public static final String RF = "removals.db";
   private String Dw;
   private Map Uv;
   private PY.eo oW;
   private Map gO;
   private Map nf;
   private static final String gP = "-------- JEB NATIVE SIGNATURE REMOVAL CONFIRMATION FILE --------"
      + Strings.LINESEP
      + "# This file is used during native signatures generation to confirm/infirm signature removal."
      + Strings.LINESEP
      + "# Possible solutions for each removal:"
      + Strings.LINESEP
      + "# - 'KEEP': signature will not be removed from the package"
      + Strings.LINESEP
      + "# - 'REMOVE': signature will be removed from the package"
      + Strings.LINESEP
      + "----------------------------------------------------"
      + Strings.LINESEP;
   private static final String za = "---------- signature to remove ----------";
   private static final String lm = "features:";
   private static final String zz = "attributes:";
   private static final String JY = ">> status:";

   public PY(String var1, PY.eo var2) {
      this.Dw = var1;
      this.Uv = new HashMap();
      this.gO = new HashMap();
      this.nf = new HashMap();
      this.oW = var2;
   }

   public PY.eo q() {
      return this.oW;
   }

   public boolean q(INativeSignature var1, PY.eo var2) {
      PY.eo var3 = var2;
      if (var2 == null) {
         var3 = this.oW;
      }

      if (this.Uv.containsKey(var1)) {
         return false;
      } else {
         this.Uv.put(var1, var3);
         this.RF(var1);
         return true;
      }
   }

   public PY.eo q(INativeSignature var1) {
      PY.eo var2 = (PY.eo)this.Uv.get(var1);
      return var2 != null ? var2 : PY.eo.xK;
   }

   public boolean RF() {
      for (PY.eo var2 : this.Uv.values()) {
         if (var2 == PY.eo.xK) {
            return false;
         }
      }

      return true;
   }

   private void RF(INativeSignature var1) {
      Assert.a(this.nf.size() == this.gO.size());
      Integer var2 = (Integer)this.nf.get(var1);
      if (var2 == null) {
         var2 = this.nf.size();
         this.gO.put(var2, var1);
         this.nf.put(var1, var2);
      }
   }

   public String xK() {
      return this.Dw;
   }

   public void Dw() {
      File var1 = new File(this.Dw, "removals.txt");
      StringBuilder var2 = new StringBuilder();
      var2.append(gP);

      for (Entry var4 : this.Uv.entrySet()) {
         var2.append("---------- signature to remove ----------");
         var2.append(Strings.LINESEP);
         this.q((INativeSignature)var4.getKey(), var2, true, true);
         this.q((PY.eo)var4.getValue(), var2);
      }

      try {
         IO.writeFile(var1, var2.toString());
      } catch (IOException var10) {
         xK.error("error when writing conflict txt file");
         return;
      }

      File var11 = new File(this.Dw, "removals.db");
      cuu var12 = cuu.q();
      SerializationManager var5 = new SerializationManager(var12);
      DirectByteArrayOutputStream var6 = new DirectByteArrayOutputStream();
      Serializer var7 = var5.getSerializer(var6);

      try {
         var7.serialize(this.gO);
         var7.close();
         IO.writeFile(var11, var6.getRawBytes(), 0, var6.size());
      } catch (IOException var9) {
         xK.catching(var9);
      }

      Object[] var10000 = new Object[]{this.gO.size()};
   }

   private void q(PY.eo var1, StringBuilder var2) {
      var2.append(">> status:");
      var2.append(Strings.LINESEP);
      var2.append(var1);
      var2.append(Strings.LINESEP);
   }

   private void q(INativeSignature var1, StringBuilder var2, boolean var3, boolean var4) {
      Integer var5 = (Integer)this.nf.get(var1);
      if (var5 == null) {
         throw new RuntimeException("unknown sig");
      } else {
         var2.append("#");
         var2.append(var5);
         var2.append(":");
         var2.append(var1.getTargetName());
         var2.append(Strings.LINESEP);
         if (var3) {
            var2.append("  ");
            var2.append("features:");
            var2.append(Strings.LINESEP);
            var2.append(((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.CU)var1).q());
            var2.append(Strings.LINESEP);
         }

         if (var4) {
            var2.append("  ");
            var2.append("attributes:");
            var2.append(var1.getAttributes());
            var2.append(Strings.LINESEP);
         }

         var2.append(Strings.LINESEP);
      }
   }

   public void Uv() {
      File var1 = new File(this.Dw.concat(File.separator).concat("removals.db"));
      if (!var1.exists()) {
         Object[] var10000 = new Object[0];
      } else {
         this.Uv.clear();
         this.gO.clear();
         this.nf.clear();

         try (FileInputStream var2 = new FileInputStream(this.Dw.concat(File.separator).concat("removals.db"))) {
            cuu var3 = cuu.q();
            SerializationManager var4 = new SerializationManager(var3);
            Deserializer var5 = var4.getDeserializer(var2);
            this.gO = (Map)var5.deserialize();
         } catch (IOException var12) {
            xK.error("Error during deserialization");
            return;
         }

         if (this.gO == null) {
            xK.error("Error during deserialization");
         } else {
            for (Entry var15 : this.gO.entrySet()) {
               this.nf.put((INativeSignature)var15.getValue(), (Integer)var15.getKey());
            }

            Assert.a(this.nf.size() == this.gO.size());
            File var14 = new File(this.Dw, "removals.txt");

            List var16;
            try {
               var16 = IO.readLines(var14);
            } catch (IOException var10) {
               xK.error("error when reading conflict txt file");
               return;
            }

            Iterator var17 = var16.iterator();

            while (var17.hasNext()) {
               String var18 = (String)var17.next();
               if (var18.startsWith("---------- signature to remove ----------")) {
                  var18 = (String)var17.next();
                  if (var18.startsWith("#")) {
                     int var6 = Integer.parseInt(var18.substring(1, var18.indexOf(58)));
                     INativeSignature var7 = (INativeSignature)this.gO.get(var6);
                     if (var7 == null) {
                        throw new RuntimeException("unknown sig");
                     }

                     while (!var18.startsWith(">> status:")) {
                        var18 = (String)var17.next();
                     }

                     var18 = (String)var17.next();
                     PY.eo var8 = this.q(var18);
                     this.q(var7, var8);
                  }
               }
            }
         }
      }
   }

   private PY.eo q(String var1) {
      switch (var1) {
         case "UNKNOWN":
            return PY.eo.xK;
         case "KEEP":
            return PY.eo.q;
         case "REMOVE":
            return PY.eo.RF;
         default:
            throw new RuntimeException("unknown status");
      }
   }

   public static enum eo {
      q,
      RF,
      xK;
   }
}
