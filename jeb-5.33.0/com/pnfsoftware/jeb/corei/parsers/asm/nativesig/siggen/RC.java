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
import com.pnfsoftware.jebglobal.ckh;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class RC {
   private static final ILogger pC = GlobalLog.getLogger(RC.class);
   private String A;
   private Map kS;
   private RC.Av wS;
   private Map UT;
   private Map E;
   private static final String sY = "-------- JEB NATIVE SIGNATURE REMOVAL CONFIRMATION FILE --------"
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

   public RC(String var1, RC.Av var2) {
      this.A = var1;
      this.kS = new HashMap();
      this.UT = new HashMap();
      this.E = new HashMap();
      this.wS = var2;
   }

   public boolean pC(INativeSignature var1, RC.Av var2) {
      RC.Av var3 = var2;
      if (var2 == null) {
         var3 = this.wS;
      }

      if (this.kS.containsKey(var1)) {
         return false;
      } else {
         this.kS.put(var1, var3);
         this.A(var1);
         return true;
      }
   }

   public RC.Av pC(INativeSignature var1) {
      RC.Av var2 = (RC.Av)this.kS.get(var1);
      return var2 != null ? var2 : RC.Av.kS;
   }

   public boolean pC() {
      for (RC.Av var2 : this.kS.values()) {
         if (var2 == RC.Av.kS) {
            return false;
         }
      }

      return true;
   }

   private void A(INativeSignature var1) {
      Assert.a(this.E.size() == this.UT.size());
      Integer var2 = (Integer)this.E.get(var1);
      if (var2 == null) {
         var2 = this.E.size();
         this.UT.put(var2, var1);
         this.E.put(var1, var2);
      }
   }

   public String A() {
      return this.A;
   }

   public void kS() {
      File var1 = new File(this.A, "removals.txt");
      StringBuilder var2 = new StringBuilder();
      var2.append(sY);

      for (Entry var4 : this.kS.entrySet()) {
         var2.append("---------- signature to remove ----------");
         var2.append(Strings.LINESEP);
         this.pC((INativeSignature)var4.getKey(), var2, true, true);
         this.pC((RC.Av)var4.getValue(), var2);
      }

      try {
         IO.writeFile(var1, var2.toString());
      } catch (IOException var10) {
         pC.error("error when writing conflict txt file");
         return;
      }

      File var11 = new File(this.A, "removals.db");
      ckh var12 = ckh.pC();
      SerializationManager var5 = new SerializationManager(var12);
      DirectByteArrayOutputStream var6 = new DirectByteArrayOutputStream();
      Serializer var7 = var5.getSerializer(var6);

      try {
         var7.serialize(this.UT);
         var7.close();
         IO.writeFile(var11, var6.getRawBytes(), 0, var6.size());
      } catch (IOException var9) {
         pC.catching(var9);
      }

      Object[] var10000 = new Object[]{this.UT.size()};
   }

   private void pC(RC.Av var1, StringBuilder var2) {
      var2.append(">> status:");
      var2.append(Strings.LINESEP);
      var2.append(var1);
      var2.append(Strings.LINESEP);
   }

   private void pC(INativeSignature var1, StringBuilder var2, boolean var3, boolean var4) {
      Integer var5 = (Integer)this.E.get(var1);
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
            var2.append(((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Sv)var1).pC());
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

   public void wS() {
      File var1 = new File(this.A.concat(File.separator).concat("removals.db"));
      if (!var1.exists()) {
         Object[] var10000 = new Object[0];
      } else {
         this.kS.clear();
         this.UT.clear();
         this.E.clear();

         try (FileInputStream var2 = new FileInputStream(this.A.concat(File.separator).concat("removals.db"))) {
            ckh var3 = ckh.pC();
            SerializationManager var4 = new SerializationManager(var3);
            Deserializer var5 = var4.getDeserializer(var2);
            this.UT = (Map)var5.deserialize();
         } catch (IOException var12) {
            pC.error("Error during deserialization");
            return;
         }

         if (this.UT == null) {
            pC.error("Error during deserialization");
         } else {
            for (Entry var15 : this.UT.entrySet()) {
               this.E.put((INativeSignature)var15.getValue(), (Integer)var15.getKey());
            }

            Assert.a(this.E.size() == this.UT.size());
            File var14 = new File(this.A, "removals.txt");

            List var16;
            try {
               var16 = IO.readLines(var14);
            } catch (IOException var10) {
               pC.error("error when reading conflict txt file");
               return;
            }

            Iterator var17 = var16.iterator();

            while (var17.hasNext()) {
               String var18 = (String)var17.next();
               if (var18.startsWith("---------- signature to remove ----------")) {
                  var18 = (String)var17.next();
                  if (var18.startsWith("#")) {
                     int var6 = Integer.parseInt(var18.substring(1, var18.indexOf(58)));
                     INativeSignature var7 = (INativeSignature)this.UT.get(var6);
                     if (var7 == null) {
                        throw new RuntimeException("unknown sig");
                     }

                     while (!var18.startsWith(">> status:")) {
                        var18 = (String)var17.next();
                     }

                     var18 = (String)var17.next();
                     RC.Av var8 = this.pC(var18);
                     this.pC(var7, var8);
                  }
               }
            }
         }
      }
   }

   private RC.Av pC(String var1) {
      switch (var1) {
         case "UNKNOWN":
            return RC.Av.kS;
         case "KEEP":
            return RC.Av.pC;
         case "REMOVE":
            return RC.Av.A;
         default:
            throw new RuntimeException("unknown status");
      }
   }

   public static enum Av {
      pC,
      A,
      kS;
   }
}
