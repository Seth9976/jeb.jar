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
import com.pnfsoftware.jebglobal.ckh;
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

public class Av {
   private static final ILogger E = GlobalLog.getLogger(Av.class);
   private Map sY;
   private Map ys;
   private cq ld;
   private cq gp;
   private String oT;
   private Map fI;
   private Map WR;
   public static final cq pC = new p();
   public static final cq A = new Sv();
   public static final cq kS = new K();
   public static final cq wS = new KD();
   public static final cq UT = new zp();
   private static final String NS = "-------- JEB NATIVE SIGNATURE CONFLICT FILE --------"
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

   public Av(String var1, cq var2, cq var3) {
      this.oT = var1;
      this.sY = new HashMap();
      this.ys = new HashMap();
      this.fI = new HashMap();
      this.WR = new HashMap();
      this.ld = var2;
      this.gp = var3;
   }

   public boolean pC(bO var1, cq var2) {
      Assert.a(var1 != null && var1.pC().size() > 1);
      if (this.sY.get(var1) != null) {
         return false;
      } else {
         cq var3 = var2;
         if (var2 == null) {
            var3 = this.ld;
         }

         this.sY.put(var1, var3);

         for (INativeSignature var5 : var1.pC()) {
            this.pC(var5);
         }

         return true;
      }
   }

   public boolean pC(rQ var1, cq var2) {
      Assert.a(var1.A() != null && var1.pC() != null && var1.pC().size() != 0);
      if (this.ys.get(var1) != null) {
         return false;
      } else {
         cq var3 = var2;
         if (var2 == null) {
            var3 = this.gp;
         }

         this.ys.put(var1, var3);
         this.pC(var1.A());

         for (INativeSignature var5 : var1.pC()) {
            this.pC(var5);
         }

         return true;
      }
   }

   private void pC(INativeSignature var1) {
      Assert.a(this.WR.size() == this.fI.size());
      Integer var2 = (Integer)this.WR.get(var1);
      if (var2 == null) {
         var2 = this.WR.size();
         this.fI.put(var2, var1);
         this.WR.put(var1, var2);
      }
   }

   public boolean pC() {
      return this.A() && this.kS();
   }

   public boolean A() {
      for (cq var2 : this.sY.values()) {
         if (var2 instanceof p) {
            return false;
         }
      }

      return true;
   }

   public boolean kS() {
      for (cq var2 : this.ys.values()) {
         if (var2 instanceof p) {
            return false;
         }
      }

      return true;
   }

   public cq pC(DH var1) {
      cq var2 = (cq)this.sY.get(var1);
      if (var2 != null) {
         return var2;
      } else {
         var2 = (cq)this.ys.get(var1);
         return var2 != null ? var2 : pC;
      }
   }

   public String wS() {
      return this.oT;
   }

   public void UT() {
      File var1 = new File(this.oT, "conflicts.txt");
      StringBuilder var2 = new StringBuilder();
      var2.append(NS);

      for (Entry var4 : this.sY.entrySet()) {
         Assert.a(((bO)var4.getKey()).pC().size() > 1);
         var2.append("---------- conflict (features equality) ----------");
         var2.append(Strings.LINESEP);
         var2.append(">> between:");
         var2.append(Strings.LINESEP);
         var2.append(Strings.LINESEP);

         for (INativeSignature var6 : ((bO)var4.getKey()).pC()) {
            this.pC((DH)var4.getKey(), var6, var2, false, true);
         }

         INativeSignature var15 = (INativeSignature)((bO)var4.getKey()).pC().iterator().next();
         var2.append("features:");
         var2.append(Strings.LINESEP);
         var2.append(((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Sv)var15).pC());
         var2.append(Strings.LINESEP);
         var2.append(Strings.LINESEP);
         this.pC((cq)var4.getValue(), var2);
      }

      for (Entry var13 : this.ys.entrySet()) {
         Assert.a(((rQ)var13.getKey()).A() != null && ((rQ)var13.getKey()).pC() != null && ((rQ)var13.getKey()).pC().size() != 0);
         var2.append("---------- conflict (features inclusion) ----------");
         var2.append(Strings.LINESEP);
         var2.append(">> signature:");
         var2.append(Strings.LINESEP);
         var2.append(Strings.LINESEP);
         this.pC((DH)var13.getKey(), ((rQ)var13.getKey()).A(), var2, true, true);
         var2.append(">> included in:");
         var2.append(Strings.LINESEP);
         var2.append(Strings.LINESEP);

         for (INativeSignature var18 : ((rQ)var13.getKey()).pC()) {
            this.pC((DH)var13.getKey(), var18, var2, true, true);
         }

         this.pC((cq)var13.getValue(), var2);
      }

      try {
         IO.writeFile(var1, var2.toString());
      } catch (IOException var10) {
         E.error("error when writing conflict txt file");
         return;
      }

      File var12 = new File(this.oT, "conflicts.db");
      ckh var14 = ckh.pC();
      SerializationManager var17 = new SerializationManager(var14);
      DirectByteArrayOutputStream var19 = new DirectByteArrayOutputStream();
      Serializer var7 = var17.getSerializer(var19);

      try {
         var7.serialize(this.fI);
         var7.close();
         IO.writeFile(var12, var19.getRawBytes(), 0, var19.size());
      } catch (IOException var9) {
         E.catching(var9);
      }

      Object[] var10000 = new Object[]{this.fI.size()};
   }

   private void pC(DH var1, INativeSignature var2, StringBuilder var3, boolean var4, boolean var5) {
      Integer var6 = (Integer)this.WR.get(var2);
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
            var3.append(((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Sv)var2).pC());
            var3.append(Strings.LINESEP);
         }

         if (var5) {
            var3.append("  ");
            var3.append("attributes:");
            var3.append(var2.getAttributes());
            var3.append(Strings.LINESEP);
         }

         List var7 = var1.pC(var2);
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

   private void pC(cq var1, StringBuilder var2) {
      var2.append(">> solution:");
      var2.append(Strings.LINESEP);
      var2.append(var1);
      var2.append(Strings.LINESEP);
   }

   public void E() {
      File var1 = new File(this.oT.concat(File.separator).concat("conflicts.db"));
      if (!var1.exists()) {
         Object[] var10000 = new Object[0];
      } else {
         this.sY.clear();
         this.fI.clear();
         this.WR.clear();

         try (FileInputStream var2 = new FileInputStream(this.oT.concat(File.separator).concat("conflicts.db"))) {
            ckh var3 = ckh.pC();
            SerializationManager var4 = new SerializationManager(var3);
            Deserializer var5 = var4.getDeserializer(var2);
            this.fI = (Map)var5.deserialize();
         } catch (IOException var20) {
            E.error("Error during deserialization");
            return;
         }

         if (this.fI == null) {
            E.error("Error during deserialization");
         } else {
            for (Entry var23 : this.fI.entrySet()) {
               this.WR.put((INativeSignature)var23.getValue(), (Integer)var23.getKey());
            }

            Assert.a(this.WR.size() == this.fI.size());
            File var22 = new File(this.oT, "conflicts.txt");

            List var24;
            try {
               var24 = IO.readLines(var22);
            } catch (IOException var18) {
               E.error("error when reading conflict txt file");
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
                        INativeSignature var8 = this.pC(var25, var7, false);
                        if (var8 == null) {
                           break;
                        }

                        var6.add(var8);
                     }

                     while (!var26.startsWith(">> solution:")) {
                        var26 = (String)var25.next();
                     }

                     var26 = (String)var25.next();
                     cq var32 = this.pC(var26);
                     bO var9 = new bO(var6, false);

                     for (INativeSignature var11 : var9.pC()) {
                        List var12 = (List)var7.get(var11);
                        if (var12 != null) {
                           for (String var14 : var12) {
                              var9.pC(var11, var14);
                           }
                        }
                     }

                     this.pC(var9, var32);
                  }
               }

               if (var26.startsWith("---------- conflict (features inclusion) ----------")) {
                  HashSet var31 = new HashSet();
                  HashMap var33 = new HashMap();
                  var26 = (String)var25.next();
                  if (var26.startsWith(">> signature:")) {
                     var25.next();
                     INativeSignature var30 = this.pC(var25, var33, true);
                     Assert.a(var30 != null);
                     var26 = (String)var25.next();
                     if (var26.startsWith(">> included in:")) {
                        var25.next();

                        while (var25.hasNext()) {
                           INativeSignature var34 = this.pC(var25, var33, true);
                           if (var34 == null) {
                              break;
                           }

                           var31.add(var34);
                        }

                        var26 = (String)var25.next();
                        cq var35 = this.pC(var26);
                        rQ var36 = new rQ(var30, var31, false);
                        List var37 = (List)var33.get(var30);
                        if (var37 != null) {
                           for (String var40 : var37) {
                              var36.pC(var30, var40);
                           }
                        }

                        for (INativeSignature var41 : var36.pC()) {
                           List var42 = (List)var33.get(var41);
                           if (var42 != null) {
                              for (String var16 : var42) {
                                 var36.pC(var41, var16);
                              }
                           }
                        }

                        this.pC(var36, var35);
                     }
                  }
               }
            }
         }
      }
   }

   private INativeSignature pC(Iterator var1, Map var2, boolean var3) {
      String var4 = (String)var1.next();
      INativeSignature var5 = null;
      if (var4.startsWith("#")) {
         int var6 = Integer.parseInt(var4.substring(1, var4.indexOf(58)));
         var5 = (INativeSignature)this.fI.get(var6);
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

   private cq pC(String var1) {
      switch (var1) {
         case "DISCARD_INCLUDED":
            return kS;
         case "DISCARD_ALL":
            return A;
         case "MERGE_AS_UNKNOWN":
            return wS;
         case "UNKNOWN":
            return pC;
         case "KEEP_ALL":
            return UT;
         default:
            if (var1.startsWith("MERGE_ON")) {
               int var4 = Integer.parseInt(var1.substring(var1.indexOf(58) + 1));
               if (!this.fI.containsKey(var4)) {
                  throw new RuntimeException("unknown signature id");
               } else {
                  return new yt(var4);
               }
            } else if (var1.startsWith("DISCARD")) {
               int var2 = Integer.parseInt(var1.substring(var1.indexOf(58) + 1));
               if (!this.fI.containsKey(var2)) {
                  throw new RuntimeException("unknown signature id");
               } else {
                  return new Ws(var2);
               }
            } else {
               return null;
            }
      }
   }

   public INativeSignature pC(Integer var1) {
      return (INativeSignature)this.fI.get(var1);
   }

   public Set sY() {
      return this.ys.keySet();
   }
}
