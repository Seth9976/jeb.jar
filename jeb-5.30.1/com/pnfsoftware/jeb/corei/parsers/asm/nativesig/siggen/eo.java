package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen;

import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeSignature;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.Maps;
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class eo {
   private static final ILogger nf = GlobalLog.getLogger(eo.class);
   private Map gP;
   private Map za;
   private Nt lm;
   private Nt zz;
   private String JY;
   private Map HF;
   private Map LK;
   public static final Nt q = new Uz();
   public static final Nt RF = new CU();
   public static final Nt xK = new nI();
   public static final Nt Dw = new qV();
   public static final Nt Uv = new EE();
   public static final String oW = "conflicts.txt";
   public static final String gO = "conflicts.db";
   private static final String io = "-------- JEB NATIVE SIGNATURE CONFLICT FILE --------"
      + Strings.LINESEP
      + "# This file is used during native signatures generation to provide a solution to signatures conflict."
      + Strings.LINESEP
      + "# Possible solutions for each equality conflict:"
      + Strings.LINESEP
      + "# - 'MERGE_AS_UNKNOWN': signatures will be merged under a generic name"
      + Strings.LINESEP
      + "# - 'MERGE_ON:X' where X is a decimal signature id, signatures will be merged under the name of the given signature"
      + Strings.LINESEP
      + "# - 'DISCARD_ALL': all signatures will be removed from the signature package"
      + Strings.LINESEP
      + "# Possible solutions for each inclusion conflict:"
      + Strings.LINESEP
      + "# - 'DISCARD_INCLUDED': the included signature will be removed from the signature package"
      + Strings.LINESEP
      + "# - 'DISCARD_ALL': all signatures will be removed from the signature package"
      + Strings.LINESEP
      + "----------------------------------------------------"
      + Strings.LINESEP;
   private static final String qa = "---------- conflict (features equality) ----------";
   private static final String Hk = ">> between:";
   private static final String Me = "---------- conflict (features inclusion) ----------";
   private static final String PV = ">> signature:";
   private static final String oQ = ">> included in:";
   private static final String xW = "features:";
   private static final String KT = "attributes:";
   private static final String Gf = ">> solution:";
   private static final String Ef = "hint:";

   public eo(String var1, Nt var2, Nt var3) {
      this.JY = var1;
      this.gP = new HashMap();
      this.za = new HashMap();
      this.HF = new HashMap();
      this.LK = new HashMap();
      this.lm = var2;
      this.zz = var3;
   }

   public boolean q(oM var1, Nt var2) {
      Assert.a(var1 != null && var1.q().size() > 1);
      if (this.gP.get(var1) != null) {
         return false;
      } else {
         Nt var3 = var2;
         if (var2 == null) {
            var3 = this.lm;
         }

         this.gP.put(var1, var3);

         for (INativeSignature var5 : var1.q()) {
            this.RF(var5);
         }

         return true;
      }
   }

   public boolean q(tw var1, Nt var2) {
      Assert.a(var1.RF() != null && var1.q() != null && var1.q().size() != 0);
      if (this.za.get(var1) != null) {
         return false;
      } else {
         Nt var3 = var2;
         if (var2 == null) {
            var3 = this.zz;
         }

         this.za.put(var1, var3);
         this.RF(var1.RF());

         for (INativeSignature var5 : var1.q()) {
            this.RF(var5);
         }

         return true;
      }
   }

   private void RF(INativeSignature var1) {
      Assert.a(this.LK.size() == this.HF.size());
      Integer var2 = (Integer)this.LK.get(var1);
      if (var2 == null) {
         var2 = this.LK.size();
         this.HF.put(var2, var1);
         this.LK.put(var1, var2);
      }
   }

   public boolean q() {
      return this.RF() && this.xK();
   }

   public boolean RF() {
      for (Nt var2 : this.gP.values()) {
         if (var2 instanceof Uz) {
            return false;
         }
      }

      return true;
   }

   public boolean xK() {
      for (Nt var2 : this.za.values()) {
         if (var2 instanceof Uz) {
            return false;
         }
      }

      return true;
   }

   public Nt q(iZ var1, Nt var2) {
      Nt var3 = (Nt)this.gP.get(var1);
      if (var3 != null) {
         this.gP.put((oM)var1, var2);
         return var3;
      } else {
         var3 = (Nt)this.za.get(var1);
         if (var3 != null) {
            this.za.put((tw)var1, var2);
            return var3;
         } else {
            throw new IllegalStateException("unknown conflict");
         }
      }
   }

   public Nt q(iZ var1) {
      Nt var2 = (Nt)this.gP.get(var1);
      if (var2 != null) {
         return var2;
      } else {
         var2 = (Nt)this.za.get(var1);
         return var2 != null ? var2 : q;
      }
   }

   public String Dw() {
      return this.JY;
   }

   public void Uv() {
      File var1 = new File(this.JY, "conflicts.txt");
      StringBuilder var2 = new StringBuilder();
      var2.append(io);

      for (Entry var4 : this.gP.entrySet()) {
         Assert.a(((oM)var4.getKey()).q().size() > 1);
         var2.append("---------- conflict (features equality) ----------");
         var2.append(Strings.LINESEP);
         var2.append(">> between:");
         var2.append(Strings.LINESEP);
         var2.append(Strings.LINESEP);

         for (INativeSignature var6 : ((oM)var4.getKey()).q()) {
            this.q((iZ)var4.getKey(), var6, var2, false, true);
         }

         INativeSignature var15 = (INativeSignature)((oM)var4.getKey()).q().iterator().next();
         var2.append("features:");
         var2.append(Strings.LINESEP);
         var2.append(((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.CU)var15).q());
         var2.append(Strings.LINESEP);
         var2.append(Strings.LINESEP);
         this.q((Nt)var4.getValue(), var2);
      }

      for (Entry var13 : this.za.entrySet()) {
         Assert.a(((tw)var13.getKey()).RF() != null && ((tw)var13.getKey()).q() != null && ((tw)var13.getKey()).q().size() != 0);
         var2.append("---------- conflict (features inclusion) ----------");
         var2.append(Strings.LINESEP);
         var2.append(">> signature:");
         var2.append(Strings.LINESEP);
         var2.append(Strings.LINESEP);
         this.q((iZ)var13.getKey(), ((tw)var13.getKey()).RF(), var2, true, true);
         var2.append(">> included in:");
         var2.append(Strings.LINESEP);
         var2.append(Strings.LINESEP);

         for (INativeSignature var18 : ((tw)var13.getKey()).q()) {
            this.q((iZ)var13.getKey(), var18, var2, true, true);
         }

         this.q((Nt)var13.getValue(), var2);
      }

      try {
         IO.writeFile(var1, var2.toString());
      } catch (IOException var10) {
         nf.error("error when writing conflict txt file");
         return;
      }

      File var12 = new File(this.JY, "conflicts.db");
      cuu var14 = cuu.q();
      SerializationManager var17 = new SerializationManager(var14);
      DirectByteArrayOutputStream var19 = new DirectByteArrayOutputStream();
      Serializer var7 = var17.getSerializer(var19);

      try {
         var7.serialize(this.HF);
         var7.close();
         IO.writeFile(var12, var19.getRawBytes(), 0, var19.size());
      } catch (IOException var9) {
         nf.catching(var9);
      }

      Object[] var10000 = new Object[]{this.HF.size()};
   }

   private void q(iZ var1, INativeSignature var2, StringBuilder var3, boolean var4, boolean var5) {
      Integer var6 = (Integer)this.LK.get(var2);
      if (var6 == null) {
         throw new RuntimeException("unknown sig");
      } else {
         var3.append("#");
         var3.append(var6);
         var3.append(":");
         var3.append(var2.getTargetName());
         var3.append(Strings.LINESEP);
         if (var4) {
            var3.append("  ");
            var3.append("features:");
            var3.append(Strings.LINESEP);
            var3.append(((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.CU)var2).q());
            var3.append(Strings.LINESEP);
         }

         if (var5) {
            var3.append("  ");
            var3.append("attributes:");
            var3.append(var2.getAttributes());
            var3.append(Strings.LINESEP);
         }

         List var7 = var1.q(var2);
         if (var7 != null && var7.size() != 0) {
            for (String var9 : var7) {
               var3.append("  ");
               var3.append("hint:");
               var3.append(var9);
               var3.append(Strings.LINESEP);
            }
         }

         var3.append(Strings.LINESEP);
      }
   }

   private void q(Nt var1, StringBuilder var2) {
      var2.append(">> solution:");
      var2.append(Strings.LINESEP);
      var2.append(var1);
      var2.append(Strings.LINESEP);
   }

   public void oW() {
      File var1 = new File(this.JY.concat(File.separator).concat("conflicts.db"));
      if (!var1.exists()) {
         Object[] var10000 = new Object[0];
      } else {
         this.gP.clear();
         this.HF.clear();
         this.LK.clear();

         try (FileInputStream var2 = new FileInputStream(this.JY.concat(File.separator).concat("conflicts.db"))) {
            cuu var3 = cuu.q();
            SerializationManager var4 = new SerializationManager(var3);
            Deserializer var5 = var4.getDeserializer(var2);
            this.HF = (Map)var5.deserialize();
         } catch (IOException var20) {
            nf.error("Error during deserialization");
            return;
         }

         if (this.HF == null) {
            nf.error("Error during deserialization");
         } else {
            for (Entry var23 : this.HF.entrySet()) {
               this.LK.put((INativeSignature)var23.getValue(), (Integer)var23.getKey());
            }

            Assert.a(this.LK.size() == this.HF.size());
            File var22 = new File(this.JY, "conflicts.txt");

            List var24;
            try {
               var24 = IO.readLines(var22);
            } catch (IOException var18) {
               nf.error("error when reading conflict txt file");
               return;
            }

            Iterator var25 = var24.iterator();

            while (var25.hasNext()) {
               String var26 = (String)var25.next();
               if (var26.startsWith("---------- conflict (features equality) ----------")) {
                  HashSet var6 = new HashSet();
                  HashMap var7 = new HashMap();
                  var26 = (String)var25.next();
                  if (var26.startsWith(">> between:")) {
                     var25.next();

                     while (var25.hasNext()) {
                        INativeSignature var8 = this.q(var25, var7, false);
                        if (var8 == null) {
                           break;
                        }

                        var6.add(var8);
                     }

                     while (!var26.startsWith(">> solution:")) {
                        var26 = (String)var25.next();
                     }

                     var26 = (String)var25.next();
                     Nt var32 = this.q(var26);
                     oM var9 = new oM(var6, false);

                     for (INativeSignature var11 : var9.q()) {
                        List var12 = (List)var7.get(var11);
                        if (var12 != null) {
                           for (String var14 : var12) {
                              var9.q(var11, var14);
                           }
                        }
                     }

                     this.q(var9, var32);
                  }
               }

               if (var26.startsWith("---------- conflict (features inclusion) ----------")) {
                  HashSet var31 = new HashSet();
                  HashMap var33 = new HashMap();
                  var26 = (String)var25.next();
                  if (var26.startsWith(">> signature:")) {
                     var25.next();
                     INativeSignature var30 = this.q(var25, var33, true);
                     Assert.a(var30 != null);
                     var26 = (String)var25.next();
                     if (var26.startsWith(">> included in:")) {
                        var25.next();

                        while (var25.hasNext()) {
                           INativeSignature var34 = this.q(var25, var33, true);
                           if (var34 == null) {
                              break;
                           }

                           var31.add(var34);
                        }

                        var26 = (String)var25.next();
                        Nt var35 = this.q(var26);
                        tw var36 = new tw(var30, var31, false);
                        List var37 = (List)var33.get(var30);
                        if (var37 != null) {
                           for (String var40 : var37) {
                              var36.q(var30, var40);
                           }
                        }

                        for (INativeSignature var41 : var36.q()) {
                           List var42 = (List)var33.get(var41);
                           if (var42 != null) {
                              for (String var16 : var42) {
                                 var36.q(var41, var16);
                              }
                           }
                        }

                        this.q(var36, var35);
                     }
                  }
               }
            }
         }
      }
   }

   private INativeSignature q(Iterator var1, Map var2, boolean var3) {
      String var4 = (String)var1.next();
      INativeSignature var5 = null;
      if (var4.startsWith("#")) {
         int var6 = Integer.parseInt(var4.substring(1, var4.indexOf(58)));
         var5 = (INativeSignature)this.HF.get(var6);
         if (var5 == null) {
            throw new RuntimeException("unknown sig");
         }

         if (var3) {
            var1.next();
            var1.next();
         }

         var1.next();

         for (String var8 = ((String)var1.next()).trim(); var8.startsWith("hint:"); var8 = ((String)var1.next()).trim()) {
            String var7 = var8.substring("hint:".length());
            Maps.putMulti(var2, var5, var7);
         }
      }

      return var5;
   }

   private Nt q(String var1) {
      switch (var1) {
         case "DISCARD_INCLUDED":
            return xK;
         case "DISCARD_ALL":
            return RF;
         case "MERGE_AS_UNKNOWN":
            return Dw;
         case "UNKNOWN":
            return q;
         case "KEEP_ALL":
            return Uv;
         default:
            if (var1.startsWith("MERGE_ON")) {
               int var4 = Integer.parseInt(var1.substring(var1.indexOf(58) + 1));
               if (!this.HF.containsKey(var4)) {
                  throw new RuntimeException("unknown signature id");
               } else {
                  return new vn(var4);
               }
            } else if (var1.startsWith("DISCARD")) {
               int var2 = Integer.parseInt(var1.substring(var1.indexOf(58) + 1));
               if (!this.HF.containsKey(var2)) {
                  throw new RuntimeException("unknown signature id");
               } else {
                  return new ej(var2);
               }
            } else {
               return null;
            }
      }
   }

   public Integer q(INativeSignature var1) {
      return (Integer)this.LK.get(var1);
   }

   public INativeSignature q(Integer var1) {
      return (INativeSignature)this.HF.get(var1);
   }

   public Set gO() {
      return this.gP.keySet();
   }

   public Set nf() {
      return this.za.keySet();
   }

   public Set gP() {
      HashSet var1 = new HashSet();
      var1.addAll(this.gO());
      var1.addAll(this.nf());
      return var1;
   }
}
