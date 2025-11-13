package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

@Ser
public class eM {
   private static final ILogger KT = GlobalLog.getLogger(eM.class);
   private static final boolean Gf = true;
   @SerId(1)
   Bu q;
   @SerId(2)
   int RF;
   @SerId(3)
   byte[] xK;
   @SerId(4)
   List Dw = new ArrayList();
   @SerId(5)
   TreeMap Uv = new TreeMap();
   @SerId(6)
   TreeMap oW = new TreeMap();
   @SerTransient
   TreeMap gO = new TreeMap();
   @SerId(7)
   List nf = new ArrayList();
   @SerId(8)
   List gP = new ArrayList();
   @SerId(10)
   TreeMap za = new TreeMap();
   @SerId(11)
   List lm = new ArrayList();
   @SerId(12)
   List zz;
   @SerId(value = 13, deprecated = true)
   @Deprecated
   private Integer Ef;
   @SerId(value = 14, deprecated = true)
   @Deprecated
   private int cC;
   @SerId(value = 15, deprecated = true)
   @Deprecated
   private int sH;
   @SerId(value = 16, deprecated = true)
   @Deprecated
   private int CE;
   @SerId(value = 17, deprecated = true)
   @Deprecated
   private Integer wF;
   @SerId(18)
   List JY = new ArrayList();
   @SerId(19)
   qV HF;
   @SerId(20)
   Integer LK;
   @SerId(21)
   Integer io;
   @SerId(22)
   Map qa;
   @SerId(23)
   String Hk;
   @SerId(24)
   String Me;
   @SerId(25)
   String PV;
   @SerTransient
   public int oQ;
   @SerTransient
   public boolean xW;

   public eM(Bu var1, byte[] var2) {
      this.q = var1;
      this.xK = var2;
   }

   public void q(int var1) {
      this.oQ = var1;
   }

   public void q(boolean var1) {
      this.xW = var1;
   }

   public void q() {
      this.zz = new ArrayList();
   }

   public Bu RF() {
      return this.q;
   }

   public byte[] xK() {
      return this.xK;
   }

   public Map Dw() {
      return this.qa == null ? Collections.emptyMap() : Collections.unmodifiableMap(this.qa);
   }

   public List Uv() {
      return this.JY;
   }

   public vX RF(int var1) {
      return (vX)this.Uv.get(var1);
   }

   public vX xK(int var1) {
      Entry var2 = this.Uv.higherEntry(var1);
      return var2 == null ? null : (vX)var2.getValue();
   }

   public vX Dw(int var1) {
      Entry var2 = this.Uv.lowerEntry(var1);
      return var2 == null ? null : (vX)var2.getValue();
   }

   public Collection q(com.pnfsoftware.jeb.corei.parsers.ethereum.eo var1, boolean var2) {
      return this.Uv.subMap(var1.q, true, var1.RF, var2).values();
   }

   public Collection q(com.pnfsoftware.jeb.corei.parsers.ethereum.eo var1) {
      return this.q(var1, true);
   }

   public Collection oW() {
      return this.oW.values();
   }

   public com.pnfsoftware.jeb.corei.parsers.ethereum.eo Uv(int var1) {
      return (com.pnfsoftware.jeb.corei.parsers.ethereum.eo)this.oW.get(var1);
   }

   public com.pnfsoftware.jeb.corei.parsers.ethereum.eo oW(int var1) {
      Entry var2 = this.oW.higherEntry(var1);
      return var2 == null ? null : (com.pnfsoftware.jeb.corei.parsers.ethereum.eo)var2.getValue();
   }

   public com.pnfsoftware.jeb.corei.parsers.ethereum.eo gO(int var1) {
      Entry var2 = this.oW.lowerEntry(var1);
      return var2 == null ? null : (com.pnfsoftware.jeb.corei.parsers.ethereum.eo)var2.getValue();
   }

   public com.pnfsoftware.jeb.corei.parsers.ethereum.eo nf(int var1) {
      return (com.pnfsoftware.jeb.corei.parsers.ethereum.eo)this.gO.get(var1);
   }

   public qx gP(int var1) {
      return (qx)this.za.get(var1);
   }

   public TreeMap gO() {
      return this.za;
   }

   public List nf() {
      ArrayList var1 = new ArrayList();

      for (qx var3 : this.za.values()) {
         if (var3 instanceof GA) {
            var1.add((GA)var3);
         }
      }

      return var1;
   }

   public int gP() {
      int var1 = 0;

      for (qx var3 : this.za.values()) {
         if (var3 instanceof GA) {
            var1++;
         }
      }

      return var1;
   }

   public List za() {
      return this.zz;
   }

   public int lm() {
      int var1 = 0;

      for (qx var3 : this.za.values()) {
         if (var3 instanceof Fw) {
            var1++;
         }
      }

      return var1;
   }

   public List zz() {
      ArrayList var1 = new ArrayList();

      for (qx var3 : this.za.values()) {
         if (var3 instanceof Fw) {
            var1.add((Fw)var3);
         }
      }

      return var1;
   }

   public Fw JY() {
      qx var1 = (qx)this.za.get(0);
      return !(var1 instanceof Fw) ? null : (Fw)var1;
   }

   public List HF() {
      HashSet var1 = new HashSet(this.oW.keySet());

      for (qx var3 : this.za.values()) {
         for (com.pnfsoftware.jeb.corei.parsers.ethereum.eo var5 : this.RF(var3)) {
            var1.remove(var5.q);
         }
      }

      ArrayList var6 = new ArrayList();

      for (int var8 : var1) {
         var6.add((com.pnfsoftware.jeb.corei.parsers.ethereum.eo)this.oW.get(var8));
      }

      return var6;
   }

   public List LK() {
      return this.nf;
   }

   public List io() {
      return this.gP;
   }

   public qV qa() {
      return this.HF;
   }

   private void oQ() {
      if (this.HF != null) {
         throw new IllegalStateException();
      } else {
         byte var1 = 1;

         while (var1 < Math.min(10, this.Dw.size()) && ((vX)this.Dw.get(var1)).q() == 80 && ((vX)this.Dw.get(var1 - 1)).Dw() != null) {
            var1 += 2;
         }

         int var2 = ((vX)this.Dw.get(var1 - 1)).zz;
         int var3 = this.xK.length - var2;
         int var4 = var2 == 0 ? 0 : 1;
         if (var3 >= 5 && this.xK[var2] == 96 && this.xK[var2 + 2] == 96 && this.xK[var2 + 3] == 64 && this.xK[var2 + 4] == 82) {
            if (this.xK[var2 + 1] == 96) {
               this.HF = qV.q(qV.eo.RF, var4);
               return;
            }

            if (this.xK[var2 + 1] == -128) {
               this.HF = qV.q(qV.eo.xK, var4);
               return;
            }
         }

         if (var3 >= 6
            && this.xK[var2] == 96
            && this.xK[var2 + 1] == 0
            && this.xK[var2 + 2] == 53
            && this.xK[var2 + 3] == 96
            && this.xK[var2 + 4] == 28
            && this.xK[var2 + 5] == 82) {
            this.HF = qV.q(qV.eo.Dw, var4);
         } else {
            this.HF = qV.q(qV.eo.q, var4);
         }
      }
   }

   public void Hk() throws ProcessorException {
      this.Me();
      this.PV();
   }

   eM.CU Me() throws ProcessorException {
      Object[] var10000 = new Object[]{this.xK.length};
      this.RF = this.xK.length;
      if (Rd.q(this.xK)) {
         boolean var1 = false;
         Rd var2 = new Rd(this.xK);

         try {
            var2.q();
         } catch (RuntimeException var7) {
            KT.catchingSilent(var7);
            var1 = true;
         } catch (Exception var8) {
            KT.catchingSilent(var8);
            var1 = true;
         }

         if (!var1) {
            this.qa = var2.RF();
            if (this.qa != null) {
               Object var3;
               if ((var3 = this.qa.get("bzzr0")) != null && var3 instanceof byte[] && ((byte[])var3).length == 32) {
                  this.Hk = Formatter.byteArrayToHexString((byte[])var3);
               }

               if ((var3 = this.qa.get("ipfs")) != null && var3 instanceof byte[] && ((byte[])var3).length == 34) {
                  this.Me = Formatter.byteArrayToHexString((byte[])var3);
               }

               if ((var3 = this.qa.get("solc")) != null && var3 instanceof byte[] && ((byte[])var3).length == 3) {
                  int var4 = ((byte[])var3)[0] & 255;
                  int var5 = ((byte[])var3)[1] & 255;
                  int var6 = ((byte[])var3)[2] & 255;
                  this.PV = Strings.ff("%d.%d.%d", var4, var5, var6);
               }
            }

            this.LK = var2.xK();
            if (this.LK != null) {
               this.io = var2.Dw() - this.LK;
               this.RF = this.LK;
               var10000 = new Object[]{this.RF};
            }
         }
      }

      int var9 = 0;

      while (var9 < this.RF) {
         vX var10 = (vX)this.q.parseAt(this.xK, var9, this.xK.length);
         var10.zz = var9;
         this.Dw.add(var10);
         this.Uv.put(var9, var10);
         if (this.oQ >= 2) {
            var10000 = new Object[]{var9, var10.getCode()[0] & 255, var10.q(null, 0)};
         }

         var9 += var10.getCode().length;
      }

      if (this.oQ >= 2) {
         var10000 = new Object[]{var9};
      }

      byte var11 = 0;
      Integer var14 = null;
      Object var15 = null;

      for (int var16 = 3; var16 < Math.min(30, this.Dw.size()); var16++) {
         vX var18 = (vX)this.Dw.get(var16);
         if (var11 == 0 && var18.q() == 57 && ((vX)this.Dw.get(var16 - 1)).Dw() != null && ((vX)this.Dw.get(var16 - 1)).lm.signum() == 0) {
            if (((vX)this.Dw.get(var16 - 2)).Dw() != null) {
               var14 = ((vX)this.Dw.get(var16 - 2)).lm.intValue();
            }

            var11 = 1;
         } else if (var11 == 1 && var18.q() == 243) {
            var11 = 2;
            break;
         }
      }

      if (var11 == 2) {
         eM.CU var17 = new eM.CU(true);
         var17.RF = var14;
         var17.xK = (Integer)var15;
         return var17;
      } else {
         return new eM.CU(false);
      }
   }

   void PV() {
      com.pnfsoftware.jeb.corei.parsers.ethereum.eo var1 = null;
      vb var2 = new vb();
      boolean var3 = false;
      vX var4 = null;
      int var5 = 0;

      for (int var6 = this.Dw.size() - 1; var5 < this.Dw.size(); var5++) {
         vX var7 = (vX)this.Dw.get(var5);
         this.Uv.put(var7.zz, var7);
         int var8 = var7.nf.RF;
         if (var1 != null && var8 == 91) {
            var1.oW = true;
            var1.Uv = var7.zz;
            var1.RF = var4.zz;
            var1.xK = var7.zz - var1.q;
            this.oW.put(var1.q, var1);
            this.gO.put(var1.RF, var1);
            var1 = null;
         }

         if (var1 == null || !var3) {
            var2.q(var7.zz);
            if (var1 == null) {
               var1 = new com.pnfsoftware.jeb.corei.parsers.ethereum.eo(var7.zz);
            }
         }

         BigInteger var9 = null;
         if (var8 == 86 || var8 == 87) {
            var9 = var2.RF();
         }

         var3 = var2.q(var7);
         if (var7.gO() || var5 == var6) {
            var1.RF = var7.zz;
            var1.xK = var7.zz + 1 - var1.q;
            if (var8 != 86 && var8 != 87) {
               var1.nf = true;
               var1.LK = !var7.gO();
            } else {
               if (var9 != null) {
                  var1.Dw = var9.intValue();
                  vX var10 = (vX)this.Uv.get(var1.Dw);
                  if (var10 == null || var10.q() != 91) {
                     Object[] var10000 = new Object[]{var1.RF, var1.Dw};
                     var1.gP = true;
                     var1.nf = var8 == 86;
                     var1.Dw = null;
                  }
               } else {
                  this.nf.add(var1);
               }

               if (var8 == 87) {
                  var1.Uv = var7.zz + 1;
               }

               var1.gO = true;
            }

            this.oW.put(var1.q, var1);
            this.gO.put(var1.RF, var1);
            var1 = null;
         }

         var4 = var7;
      }

      for (com.pnfsoftware.jeb.corei.parsers.ethereum.eo var23 : this.oW.values()) {
         if (var23.Dw != null) {
            ((com.pnfsoftware.jeb.corei.parsers.ethereum.eo)this.oW.get(var23.Dw)).io.add(var23.q);
         }

         if (var23.Uv != null) {
            ((com.pnfsoftware.jeb.corei.parsers.ethereum.eo)this.oW.get(var23.Uv)).io.add(var23.q);
         }
      }

      for (com.pnfsoftware.jeb.corei.parsers.ethereum.eo var24 : this.oW.values()) {
         if (var24.q != 0 && var24.io.isEmpty()) {
            this.gP.add(var24);
         }
      }

      ArrayList var22 = new ArrayList();

      int var25;
      do {
         var25 = 0;

         for (com.pnfsoftware.jeb.corei.parsers.ethereum.eo var34 : this.oW.values()) {
            if (!var34.za) {
               vX var11 = (vX)this.Uv.get(var34.RF);
               if (var11.q() == 86) {
                  int var12 = var34.RF + 1;
                  if (var34.Dw != null && var34.Dw != var12) {
                     vX var13 = (vX)this.Uv.get(var12);
                     if (var13 != null && var13.q() == 91) {
                        int var14 = var34.q;

                        for (vX var16 : this.Uv.descendingMap().subMap(var34.RF, false, var14, true).values()) {
                           if (var16.gP() || var16.q() == 87 || var16.q() == 86 && !((com.pnfsoftware.jeb.corei.parsers.ethereum.eo)this.gO.get(var16.zz)).za) {
                              break;
                           }

                           if (var16.lm != null && var16.lm.intValue() == var12) {
                              var22.add(var34);
                              var34.za = true;
                              var25++;
                              break;
                           }
                        }
                     }
                  }
               }
            }
         }
      } while (var25 != 0);

      Zu var26 = new Zu(this, 0);

      try {
         if (var26.q()) {
            for (Zu.CU var35 : var26.oW.values()) {
               ((com.pnfsoftware.jeb.corei.parsers.ethereum.eo)this.oW.get(var35.q)).HF = true;
               this.za.put(var35.q, new Fw(var35.q, var35.RF, var35.xK));
            }

            for (Zu.eo var36 : var26.gO.values()) {
               ((com.pnfsoftware.jeb.corei.parsers.ethereum.eo)this.oW.get(var36.q)).lm = true;
            }

            for (Zu.nI var37 : var26.nf.values()) {
               ((com.pnfsoftware.jeb.corei.parsers.ethereum.eo)this.oW.get(var37.q)).zz = true;
            }

            for (com.pnfsoftware.jeb.corei.parsers.ethereum.eo var38 : this.oW.values()) {
               if (var38.q() && var38.xK()) {
                  List var43 = var38.Dw();
                  if (var43.size() == 1) {
                     var38.Dw = (Integer)var43.get(0);
                  }
               }
            }
         }
      } catch (Exception var19) {
         KT.catching(var19);
         JebCoreService.notifySilentExceptionToClient(var19);
      }

      Object[] var61 = new Object[0];
      this.lm = this.q(this.HF());
      var61 = new Object[]{this.lm.size()};

      for (GA var39 : this.lm) {
         this.za.put(var39.Dw(), var39);
      }

      var61 = new Object[0];
      Fw var33 = new Fw(0, 0, 0);
      this.za.put(var33.oW, var33);

      for (com.pnfsoftware.jeb.corei.parsers.ethereum.eo var44 : this.HF()) {
         int var47 = var44.q;
         Entry var50 = this.oW.lowerEntry(var47);
         if (var50 != null) {
            com.pnfsoftware.jeb.corei.parsers.ethereum.eo var53 = (com.pnfsoftware.jeb.corei.parsers.ethereum.eo)var50.getValue();
            if (var53.q + var53.xK == var44.q && var53.za && !var53.lm) {
               vX var56 = (vX)this.Uv.get(var53.RF);
               if (var56.q() == 86) {
                  var53.lm = true;
                  if (var53.Dw != null && this.za.get(var53.Dw) == null) {
                     Fw var59 = new Fw(var53.Dw, 0, 0);
                     var59.nf = 3;
                     this.za.put(var53.Dw, var59);
                  }
               }
            }
         }
      }

      var61 = new Object[0];

      for (com.pnfsoftware.jeb.corei.parsers.ethereum.eo var45 : this.oW.values()) {
         vX var48 = (vX)this.Uv.get(var45.RF);
         var48.HF = var45.Dw;
         if (var45.lm) {
            var48.JY |= 1;
            if (!var45.Uv()) {
               var48.JY |= 8;
            }

            if (var45.Uv() && var45.oW()) {
               var48.JY |= 16;
            }
         }

         if (var45.zz) {
            var48.JY |= 2;
         }

         if (var45.gP) {
            var48.JY |= 4;
         }
      }

      var61 = new Object[0];
      new eM.eo(true);
      ArrayList var42 = new ArrayList(this.zz());
      var42.addAll(this.lm);
      ArrayList var46 = new ArrayList();
      qa var49 = com.pnfsoftware.jeb.corei.parsers.ethereum.qa.q();

      for (GA var54 : this.lm) {
         int var57 = var54.Uv();
         var46.add(var57);
         List var60 = var49.xK(var57);
         if (var60.size() >= 1) {
            var54.lm = (String[])var60.get(0);
            String var17 = var54.lm[0];
            if (var54.za.size() == 1) {
               qx var18 = (qx)var54.za.iterator().next();
               if (var18 != null) {
                  var18.lm = new String[]{"__impl_" + var17};
               }
            }
         }
      }

      SG var52 = SG.q();

      for (SG.eo var58 : var52.q(var46)) {
         this.JY.add(var58.q());
      }

      this.oQ();
   }

   public String q(qx var1) {
      List var2 = this.RF(var1);
      TreeMap var3 = new TreeMap();

      for (com.pnfsoftware.jeb.corei.parsers.ethereum.eo var5 : var2) {
         var3.put(var5.q, var5);
      }

      int var13 = var1.Dw();
      Integer var14 = null;
      StringBuilder var6 = new StringBuilder();

      for (com.pnfsoftware.jeb.corei.parsers.ethereum.eo var8 : var3.values()) {
         int var9 = var8.q;
         if (var14 != null && var9 != var14) {
            var6.append("      (not in routine)\n");

            for (vX var11 : this.Uv.subMap(var14, var14 + 1).values()) {
               Strings.ff(var6, "      0x%X: %02X %-40s\n", var11.zz, var11.getCode()[0] & 255, var11.q(null, 1));
            }

            var6.append("      ...\n");
         }

         if (var14 == null && var9 != var13) {
            var6.append("(not the entry-point)\n");
         }

         int var15 = 0;

         for (vX var12 : this.q(var8)) {
            Strings.ff(var6, "%s 0x%X: %02X %-40s\n", var12.zz == var13 ? ">" : (var15 == 0 ? "+" : ":"), var12.zz, var12.getCode()[0] & 255, var12.q(null, 1));
            var15++;
            var14 = var12.zz + var12.getSize();
         }
      }

      return var6.toString();
   }

   List RF(qx var1) {
      return this.za(var1.Dw());
   }

   List za(int var1) {
      ArrayList var2 = new ArrayList();
      HashSet var3 = new HashSet();
      ArrayDeque var4 = new ArrayDeque();
      var4.add(var1);

      while (!var4.isEmpty()) {
         int var5 = (Integer)var4.remove();
         if (var3.add(var5)) {
            com.pnfsoftware.jeb.corei.parsers.ethereum.eo var6 = (com.pnfsoftware.jeb.corei.parsers.ethereum.eo)this.oW.get(var5);
            if (var6 != null) {
               var2.add(var6);
               if (var6.Uv != null) {
                  var4.add(var6.Uv);
               }

               if (var6.lm) {
                  var4.addAll(var6.gO());
               } else if (var6.Dw != null) {
                  var4.add(var6.Dw);
               }
            }
         }
      }

      return var2;
   }

   List xK(qx var1) {
      ArrayList var2 = new ArrayList();

      for (com.pnfsoftware.jeb.corei.parsers.ethereum.eo var4 : this.za(var1.Dw())) {
         if (var4.nf || var4.zz) {
            var2.add(var4);
         }
      }

      return var2;
   }

   List Dw(qx var1) {
      ArrayList var2 = new ArrayList();

      for (com.pnfsoftware.jeb.corei.parsers.ethereum.eo var4 : this.za(var1.Dw())) {
         if (var4.zz) {
            var2.add(var4);
         }
      }

      return var2;
   }

   List Uv(qx var1) {
      ArrayList var2 = new ArrayList();

      for (com.pnfsoftware.jeb.corei.parsers.ethereum.eo var4 : this.za(var1.Dw())) {
         if (var4.nf) {
            var2.add(var4);
         }
      }

      return var2;
   }

   public int[] oW(qx var1) {
      int[] var2 = new int[256];

      for (com.pnfsoftware.jeb.corei.parsers.ethereum.eo var4 : this.RF(var1)) {
         for (vX var6 : this.q(var4)) {
            int var7 = var6.q() & 0xFF;
            var2[var7]++;
         }
      }

      return var2;
   }

   private List q(Collection var1) {
      if (var1 == null) {
         var1 = this.oW.values();
      }

      ArrayList var2 = new ArrayList();

      for (com.pnfsoftware.jeb.corei.parsers.ethereum.eo var4 : var1) {
         if (var4.Dw != null && var4.Uv != null) {
            ArrayList var5 = new ArrayList(this.q(var4, false));
            int var6 = var5.size();
            byte var7 = 0;
            int var8 = var6 - 2;
            if (var8 >= 1 && ((vX)var5.get(var8)).q() == 144 && ((vX)var5.get(var8 - 1)).Dw() != null) {
               var7 |= 2;
               var8 -= 2;
            }

            if (var8 >= 0 && ((vX)var5.get(var8)).q() == 20) {
               var8--;
               if (var8 >= 0 && ((vX)var5.get(var8)).xK() != null) {
                  var7 |= 1;
                  var8--;
               }

               if (var8 >= 0) {
                  vX var9 = (vX)var5.get(var8);
                  int var10 = var9.q();
                  if (var10 == 99 || var10 == 98 || var10 == 97 || var10 == 96) {
                     int var11 = var9.lm.intValue();
                     if (this.lm(var11)) {
                        var4.lm = true;
                        var2.add(new GA(var4.Dw, var11, var7));
                        continue;
                     }
                  }
               }
            }

            if (this.zz != null) {
               for (int var13 = var6 - 1; var13 >= Math.max(0, var6 - 10); var13--) {
                  vX var14 = (vX)var5.get(var13);
                  int var15 = var14.q();
                  if (var15 == 99) {
                     int var16 = var14.lm.intValue();
                     if (this.zz(var16)) {
                        this.zz.add(var14);
                     }
                  }
               }
            }
         }
      }

      return var2;
   }

   private boolean lm(int var1) {
      if (var1 != 0
         && var1 != -1
         && var1 != -16777216
         && var1 != -65536
         && var1 != -256
         && var1 != 16711680
         && var1 != 16776960
         && var1 != 16777215
         && var1 != 65280
         && var1 != 65535
         && var1 != 255) {
         int var2;
         for (var2 = 0; var1 != 0; var1 >>>= 1) {
            if ((var1 & 1) == 1) {
               var2++;
            }
         }

         return var2 >= 6 && var2 <= 26;
      } else {
         return false;
      }
   }

   private boolean zz(int var1) {
      int var2 = var1 & 0xFF;
      int var3 = var1 >>> 8 & 0xFF;
      if (var3 == var2) {
         return false;
      } else {
         int var4 = var1 >>> 16 & 0xFF;
         if (var4 != var2 && var4 != var3) {
            int var5 = var1 >>> 24 & 0xFF;
            return var5 != var2 && var5 != var3 && var5 != var4;
         } else {
            return false;
         }
      }
   }

   public static class CU {
      boolean q;
      Integer RF;
      Integer xK;

      CU(boolean var1) {
         this.q = var1;
      }
   }

   class eo {
      int q;
      Set RF = new HashSet();
      List xK = new ArrayList();

      eo(boolean var2) {
         for (qx var4 : eM.this.za.values()) {
            this.q(var4);
         }

         if (var2) {
            qx var5 = (qx)eM.this.za.get(0);

            while (true) {
               this.RF(var5);
               if (this.q == 0) {
                  break;
               }

               this.q = 0;
               this.RF.clear();
               this.xK.clear();
            }
         }
      }

      void q(qx var1) {
         HashSet var2 = new HashSet();
         ArrayDeque var3 = new ArrayDeque();
         var3.add(var1.Dw());
         int var4 = 0;
         int var5 = 0;
         int var6 = 0;
         qx.CU var7 = qx.CU.q;

         while (!var3.isEmpty()) {
            int var8 = (Integer)var3.remove();
            if (var2.add(var8)) {
               com.pnfsoftware.jeb.corei.parsers.ethereum.eo var9 = (com.pnfsoftware.jeb.corei.parsers.ethereum.eo)eM.this.oW.get(var8);
               if (var9 == null) {
                  if (var8 != eM.this.RF) {
                     throw new RuntimeException(Strings.ff("No block at offset 0x%X", var8));
                  }
               } else {
                  if (var7 != qx.CU.xK) {
                     for (vX var11 : eM.this.q(var9)) {
                        qx.CU var12 = var11.lm();
                        if (var12.compareTo(var7) > 0) {
                           var7 = var12;
                           if (var12 == qx.CU.xK) {
                              break;
                           }
                        }
                     }
                  }

                  if (var9.q()) {
                     var6++;
                  } else {
                     if (var9.zz) {
                        var4++;
                     }

                     if (var9.nf) {
                        var5++;
                     }
                  }

                  if (var9.Uv != null) {
                     var3.add(var9.Uv);
                  }

                  if (var9.lm) {
                     if (var9.Dw != null) {
                        qx var14 = (qx)eM.this.za.get(var9.Dw);
                        var1.za.add(var14);
                        var14.gP.add(var1);
                     }

                     var3.addAll(var9.gO());
                  } else if (var9.Dw != null) {
                     var3.add(var9.Dw);
                  }
               }
            }
         }

         byte var13 = 0;
         if (var4 != 0 && var5 == 0) {
            var13 = 1;
         } else if (var4 == 0 && var5 != 0) {
            var13 = 2;
         } else if (var4 != 0 && var5 != 0) {
            var13 = 3;
         }

         var1.nf = var6 == 0 ? var13 : -var13;
         var1.gO = var7;
      }

      void RF(qx var1) {
         if (this.RF.add(var1)) {
            qx.CU var2 = var1.gO;

            for (int var3 = 0; var3 < this.xK.size(); var3++) {
               qx.CU var4 = (qx.CU)this.xK.get(var3);
               if (var4.compareTo(var2) < 0) {
                  this.xK.set(var3, var2);
               }
            }

            this.xK.add(var2);

            for (qx var7 : var1.za) {
               this.RF(var7);
            }

            qx.CU var6 = (qx.CU)this.xK.remove(this.xK.size() - 1);
            if (var1.gO != var6) {
               var1.gO = var6;
               this.q++;
            }
         }
      }
   }
}
