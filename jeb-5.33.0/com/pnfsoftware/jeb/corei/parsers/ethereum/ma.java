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
public class ma {
   private static final ILogger rl = GlobalLog.getLogger(ma.class);
   @SerId(1)
   HE pC;
   @SerId(2)
   int A;
   @SerId(3)
   byte[] kS;
   @SerId(4)
   List wS = new ArrayList();
   @SerId(5)
   TreeMap UT = new TreeMap();
   @SerId(6)
   TreeMap E = new TreeMap();
   @SerTransient
   TreeMap sY = new TreeMap();
   @SerId(7)
   List ys = new ArrayList();
   @SerId(8)
   List ld = new ArrayList();
   @SerId(10)
   TreeMap gp = new TreeMap();
   @SerId(11)
   List oT = new ArrayList();
   @SerId(12)
   List fI;
   @SerId(value = 13, deprecated = true)
   @Deprecated
   private Integer z;
   @SerId(value = 14, deprecated = true)
   @Deprecated
   private int Ek;
   @SerId(value = 15, deprecated = true)
   @Deprecated
   private int hK;
   @SerId(value = 16, deprecated = true)
   @Deprecated
   private int Er;
   @SerId(value = 17, deprecated = true)
   @Deprecated
   private Integer FE;
   @SerId(18)
   List WR = new ArrayList();
   @SerId(19)
   zp NS;
   @SerId(20)
   Integer vP;
   @SerId(21)
   Integer xC;
   @SerId(22)
   Map ED;
   @SerId(23)
   String Sc;
   @SerId(24)
   String ah;
   @SerId(25)
   String eP;
   @SerTransient
   public int UO;
   @SerTransient
   public boolean Ab;

   public ma(HE var1, byte[] var2) {
      this.pC = var1;
      this.kS = var2;
   }

   public Map pC() {
      return this.ED == null ? Collections.emptyMap() : Collections.unmodifiableMap(this.ED);
   }

   public List A() {
      return this.WR;
   }

   public Mh pC(int var1) {
      Entry var2 = this.UT.lowerEntry(var1);
      return var2 == null ? null : (Mh)var2.getValue();
   }

   public Collection pC(com.pnfsoftware.jeb.corei.parsers.ethereum.Av var1, boolean var2) {
      return this.UT.subMap(var1.pC, true, var1.A, var2).values();
   }

   public Collection pC(com.pnfsoftware.jeb.corei.parsers.ethereum.Av var1) {
      return this.pC(var1, true);
   }

   public com.pnfsoftware.jeb.corei.parsers.ethereum.Av A(int var1) {
      return (com.pnfsoftware.jeb.corei.parsers.ethereum.Av)this.E.get(var1);
   }

   public List kS() {
      ArrayList var1 = new ArrayList();

      for (eW var3 : this.gp.values()) {
         if (var3 instanceof nA) {
            var1.add((nA)var3);
         }
      }

      return var1;
   }

   public List wS() {
      HashSet var1 = new HashSet(this.E.keySet());

      for (eW var3 : this.gp.values()) {
         for (com.pnfsoftware.jeb.corei.parsers.ethereum.Av var5 : this.pC(var3)) {
            var1.remove(var5.pC);
         }
      }

      ArrayList var6 = new ArrayList();

      for (int var8 : var1) {
         var6.add((com.pnfsoftware.jeb.corei.parsers.ethereum.Av)this.E.get(var8));
      }

      return var6;
   }

   public zp UT() {
      return this.NS;
   }

   private void ys() {
      if (this.NS != null) {
         throw new IllegalStateException();
      } else {
         byte var1 = 1;

         while (var1 < Math.min(10, this.wS.size()) && ((Mh)this.wS.get(var1)).pC() == 80 && ((Mh)this.wS.get(var1 - 1)).wS() != null) {
            var1 += 2;
         }

         int var2 = ((Mh)this.wS.get(var1 - 1)).UT;
         int var3 = this.kS.length - var2;
         int var4 = var2 == 0 ? 0 : 1;
         if (var3 >= 5 && this.kS[var2] == 96 && this.kS[var2 + 2] == 96 && this.kS[var2 + 3] == 64 && this.kS[var2 + 4] == 82) {
            if (this.kS[var2 + 1] == 96) {
               this.NS = zp.pC(zp.Av.A, var4);
               return;
            }

            if (this.kS[var2 + 1] == -128) {
               this.NS = zp.pC(zp.Av.kS, var4);
               return;
            }
         }

         if (var3 >= 6
            && this.kS[var2] == 96
            && this.kS[var2 + 1] == 0
            && this.kS[var2 + 2] == 53
            && this.kS[var2 + 3] == 96
            && this.kS[var2 + 4] == 28
            && this.kS[var2 + 5] == 82) {
            this.NS = zp.pC(zp.Av.wS, var4);
         } else {
            this.NS = zp.pC(zp.Av.pC, var4);
         }
      }
   }

   ma.Sv E() throws ProcessorException {
      Object[] var10000 = new Object[]{this.kS.length};
      this.A = this.kS.length;
      if (co.pC(this.kS)) {
         boolean var1 = false;
         co var2 = new co(this.kS);

         try {
            var2.pC();
         } catch (RuntimeException var7) {
            rl.catchingSilent(var7);
            var1 = true;
         } catch (Exception var8) {
            rl.catchingSilent(var8);
            var1 = true;
         }

         if (!var1) {
            this.ED = var2.A();
            if (this.ED != null) {
               Object var3;
               if ((var3 = this.ED.get("bzzr0")) != null && var3 instanceof byte[] && ((byte[])var3).length == 32) {
                  this.Sc = Formatter.byteArrayToHexString((byte[])var3);
               }

               if ((var3 = this.ED.get("ipfs")) != null && var3 instanceof byte[] && ((byte[])var3).length == 34) {
                  this.ah = Formatter.byteArrayToHexString((byte[])var3);
               }

               if ((var3 = this.ED.get("solc")) != null && var3 instanceof byte[] && ((byte[])var3).length == 3) {
                  int var4 = ((byte[])var3)[0] & 255;
                  int var5 = ((byte[])var3)[1] & 255;
                  int var6 = ((byte[])var3)[2] & 255;
                  this.eP = Strings.ff("%d.%d.%d", var4, var5, var6);
               }
            }

            this.vP = var2.kS();
            if (this.vP != null) {
               this.xC = var2.wS() - this.vP;
               this.A = this.vP;
               var10000 = new Object[]{this.A};
            }
         }
      }

      int var9 = 0;

      while (var9 < this.A) {
         Mh var10 = (Mh)this.pC.parseAt(this.kS, var9, this.kS.length);
         var10.UT = var9;
         this.wS.add(var10);
         this.UT.put(var9, var10);
         if (this.UO >= 2) {
            var10000 = new Object[]{var9, var10.getCode()[0] & 255, var10.pC(null, 0)};
         }

         var9 += var10.getCode().length;
      }

      if (this.UO >= 2) {
         var10000 = new Object[]{var9};
      }

      byte var11 = 0;
      Integer var14 = null;
      Object var15 = null;

      for (int var16 = 3; var16 < Math.min(30, this.wS.size()); var16++) {
         Mh var18 = (Mh)this.wS.get(var16);
         if (var11 == 0 && var18.pC() == 57 && ((Mh)this.wS.get(var16 - 1)).wS() != null && ((Mh)this.wS.get(var16 - 1)).wS.signum() == 0) {
            if (((Mh)this.wS.get(var16 - 2)).wS() != null) {
               var14 = ((Mh)this.wS.get(var16 - 2)).wS.intValue();
            }

            var11 = 1;
         } else if (var11 == 1 && var18.pC() == 243) {
            var11 = 2;
            break;
         }
      }

      if (var11 == 2) {
         ma.Sv var17 = new ma.Sv(true);
         var17.A = var14;
         var17.kS = (Integer)var15;
         return var17;
      } else {
         return new ma.Sv(false);
      }
   }

   void sY() {
      com.pnfsoftware.jeb.corei.parsers.ethereum.Av var1 = null;
      RC var2 = new RC();
      boolean var3 = false;
      Mh var4 = null;
      int var5 = 0;

      for (int var6 = this.wS.size() - 1; var5 < this.wS.size(); var5++) {
         Mh var7 = (Mh)this.wS.get(var5);
         this.UT.put(var7.UT, var7);
         int var8 = var7.pC.A;
         if (var1 != null && var8 == 91) {
            var1.E = true;
            var1.UT = var7.UT;
            var1.A = var4.UT;
            var1.kS = var7.UT - var1.pC;
            this.E.put(var1.pC, var1);
            this.sY.put(var1.A, var1);
            var1 = null;
         }

         if (var1 == null || !var3) {
            var2.pC(var7.UT);
            if (var1 == null) {
               var1 = new com.pnfsoftware.jeb.corei.parsers.ethereum.Av(var7.UT);
            }
         }

         BigInteger var9 = null;
         if (var8 == 86 || var8 == 87) {
            var9 = var2.pC();
         }

         var3 = var2.pC(var7);
         if (var7.sY() || var5 == var6) {
            var1.A = var7.UT;
            var1.kS = var7.UT + 1 - var1.pC;
            if (var8 != 86 && var8 != 87) {
               var1.ys = true;
               var1.vP = !var7.sY();
            } else {
               if (var9 != null) {
                  var1.wS = var9.intValue();
                  Mh var10 = (Mh)this.UT.get(var1.wS);
                  if (var10 == null || var10.pC() != 91) {
                     Object[] var10000 = new Object[]{var1.A, var1.wS};
                     var1.ld = true;
                     var1.ys = var8 == 86;
                     var1.wS = null;
                  }
               } else {
                  this.ys.add(var1);
               }

               if (var8 == 87) {
                  var1.UT = var7.UT + 1;
               }

               var1.sY = true;
            }

            this.E.put(var1.pC, var1);
            this.sY.put(var1.A, var1);
            var1 = null;
         }

         var4 = var7;
      }

      for (com.pnfsoftware.jeb.corei.parsers.ethereum.Av var23 : this.E.values()) {
         if (var23.wS != null) {
            ((com.pnfsoftware.jeb.corei.parsers.ethereum.Av)this.E.get(var23.wS)).xC.add(var23.pC);
         }

         if (var23.UT != null) {
            ((com.pnfsoftware.jeb.corei.parsers.ethereum.Av)this.E.get(var23.UT)).xC.add(var23.pC);
         }
      }

      for (com.pnfsoftware.jeb.corei.parsers.ethereum.Av var24 : this.E.values()) {
         if (var24.pC != 0 && var24.xC.isEmpty()) {
            this.ld.add(var24);
         }
      }

      ArrayList var22 = new ArrayList();

      int var25;
      do {
         var25 = 0;

         for (com.pnfsoftware.jeb.corei.parsers.ethereum.Av var34 : this.E.values()) {
            if (!var34.gp) {
               Mh var11 = (Mh)this.UT.get(var34.A);
               if (var11.pC() == 86) {
                  int var12 = var34.A + 1;
                  if (var34.wS != null && var34.wS != var12) {
                     Mh var13 = (Mh)this.UT.get(var12);
                     if (var13 != null && var13.pC() == 91) {
                        int var14 = var34.pC;

                        for (Mh var16 : this.UT.descendingMap().subMap(var34.A, false, var14, true).values()) {
                           if (var16.ys() || var16.pC() == 87 || var16.pC() == 86 && !((com.pnfsoftware.jeb.corei.parsers.ethereum.Av)this.sY.get(var16.UT)).gp
                              )
                            {
                              break;
                           }

                           if (var16.wS != null && var16.wS.intValue() == var12) {
                              var22.add(var34);
                              var34.gp = true;
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

      Hv var26 = new Hv(this, 0);

      try {
         if (var26.pC()) {
            for (Hv.Sv var35 : var26.UT.values()) {
               ((com.pnfsoftware.jeb.corei.parsers.ethereum.Av)this.E.get(var35.pC)).NS = true;
               this.gp.put(var35.pC, new nA(var35.pC, var35.A, var35.kS));
            }

            for (Hv.Av var36 : var26.E.values()) {
               ((com.pnfsoftware.jeb.corei.parsers.ethereum.Av)this.E.get(var36.pC)).oT = true;
            }

            for (Hv.K var37 : var26.sY.values()) {
               ((com.pnfsoftware.jeb.corei.parsers.ethereum.Av)this.E.get(var37.pC)).fI = true;
            }

            for (com.pnfsoftware.jeb.corei.parsers.ethereum.Av var38 : this.E.values()) {
               if (var38.pC() && var38.A()) {
                  List var43 = var38.kS();
                  if (var43.size() == 1) {
                     var38.wS = (Integer)var43.get(0);
                  }
               }
            }
         }
      } catch (Exception var19) {
         rl.catching(var19);
         JebCoreService.notifySilentExceptionToClient(var19);
      }

      Object[] var61 = new Object[0];
      this.oT = this.pC(this.wS());
      var61 = new Object[]{this.oT.size()};

      for (m var39 : this.oT) {
         this.gp.put(var39.A(), var39);
      }

      var61 = new Object[0];
      nA var33 = new nA(0, 0, 0);
      this.gp.put(var33.kS, var33);

      for (com.pnfsoftware.jeb.corei.parsers.ethereum.Av var44 : this.wS()) {
         int var47 = var44.pC;
         Entry var50 = this.E.lowerEntry(var47);
         if (var50 != null) {
            com.pnfsoftware.jeb.corei.parsers.ethereum.Av var53 = (com.pnfsoftware.jeb.corei.parsers.ethereum.Av)var50.getValue();
            if (var53.pC + var53.kS == var44.pC && var53.gp && !var53.oT) {
               Mh var56 = (Mh)this.UT.get(var53.A);
               if (var56.pC() == 86) {
                  var53.oT = true;
                  if (var53.wS != null && this.gp.get(var53.wS) == null) {
                     nA var59 = new nA(var53.wS, 0, 0);
                     var59.UT = 3;
                     this.gp.put(var53.wS, var59);
                  }
               }
            }
         }
      }

      var61 = new Object[0];

      for (com.pnfsoftware.jeb.corei.parsers.ethereum.Av var45 : this.E.values()) {
         Mh var48 = (Mh)this.UT.get(var45.A);
         var48.sY = var45.wS;
         if (var45.oT) {
            var48.E |= 1;
            if (!var45.wS()) {
               var48.E |= 8;
            }

            if (var45.wS() && var45.UT()) {
               var48.E |= 16;
            }
         }

         if (var45.fI) {
            var48.E |= 2;
         }

         if (var45.ld) {
            var48.E |= 4;
         }
      }

      var61 = new Object[0];
      new ma.Av(true);
      ArrayList var42 = new ArrayList(this.kS());
      var42.addAll(this.oT);
      ArrayList var46 = new ArrayList();
      io var49 = io.pC();

      for (m var54 : this.oT) {
         int var57 = var54.kS();
         var46.add(var57);
         List var60 = var49.pC(var57);
         if (var60.size() >= 1) {
            var54.ys = (String[])var60.get(0);
            String var17 = var54.ys[0];
            if (var54.sY.size() == 1) {
               eW var18 = (eW)var54.sY.iterator().next();
               if (var18 != null) {
                  var18.ys = new String[]{"__impl_" + var17};
               }
            }
         }
      }

      Kr var52 = Kr.pC();

      for (Kr.Av var58 : var52.pC(var46)) {
         this.WR.add(var58.pC());
      }

      this.ys();
   }

   List pC(eW var1) {
      return this.kS(var1.A());
   }

   List kS(int var1) {
      ArrayList var2 = new ArrayList();
      HashSet var3 = new HashSet();
      ArrayDeque var4 = new ArrayDeque();
      var4.add(var1);

      while (!var4.isEmpty()) {
         int var5 = (Integer)var4.remove();
         if (var3.add(var5)) {
            com.pnfsoftware.jeb.corei.parsers.ethereum.Av var6 = (com.pnfsoftware.jeb.corei.parsers.ethereum.Av)this.E.get(var5);
            if (var6 != null) {
               var2.add(var6);
               if (var6.UT != null) {
                  var4.add(var6.UT);
               }

               if (var6.oT) {
                  var4.addAll(var6.E());
               } else if (var6.wS != null) {
                  var4.add(var6.wS);
               }
            }
         }
      }

      return var2;
   }

   private List pC(Collection var1) {
      if (var1 == null) {
         var1 = this.E.values();
      }

      ArrayList var2 = new ArrayList();

      for (com.pnfsoftware.jeb.corei.parsers.ethereum.Av var4 : var1) {
         if (var4.wS != null && var4.UT != null) {
            ArrayList var5 = new ArrayList(this.pC(var4, false));
            int var6 = var5.size();
            byte var7 = 0;
            int var8 = var6 - 2;
            if (var8 >= 1 && ((Mh)var5.get(var8)).pC() == 144 && ((Mh)var5.get(var8 - 1)).wS() != null) {
               var7 |= 2;
               var8 -= 2;
            }

            if (var8 >= 0 && ((Mh)var5.get(var8)).pC() == 20) {
               var8--;
               if (var8 >= 0 && ((Mh)var5.get(var8)).kS() != null) {
                  var7 |= 1;
                  var8--;
               }

               if (var8 >= 0) {
                  Mh var9 = (Mh)var5.get(var8);
                  int var10 = var9.pC();
                  if (var10 == 99 || var10 == 98 || var10 == 97 || var10 == 96) {
                     int var11 = var9.wS.intValue();
                     if (this.wS(var11)) {
                        var4.oT = true;
                        var2.add(new m(var4.wS, var11, var7));
                        continue;
                     }
                  }
               }
            }

            if (this.fI != null) {
               for (int var13 = var6 - 1; var13 >= Math.max(0, var6 - 10); var13--) {
                  Mh var14 = (Mh)var5.get(var13);
                  int var15 = var14.pC();
                  if (var15 == 99) {
                     int var16 = var14.wS.intValue();
                     if (this.UT(var16)) {
                        this.fI.add(var14);
                     }
                  }
               }
            }
         }
      }

      return var2;
   }

   private boolean wS(int var1) {
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

   private boolean UT(int var1) {
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

   class Av {
      int pC;
      Set A = new HashSet();
      List kS = new ArrayList();

      Av(boolean var2) {
         for (eW var4 : ma.this.gp.values()) {
            this.pC(var4);
         }

         if (var2) {
            eW var5 = (eW)ma.this.gp.get(0);

            while (true) {
               this.A(var5);
               if (this.pC == 0) {
                  break;
               }

               this.pC = 0;
               this.A.clear();
               this.kS.clear();
            }
         }
      }

      void pC(eW var1) {
         HashSet var2 = new HashSet();
         ArrayDeque var3 = new ArrayDeque();
         var3.add(var1.A());
         int var4 = 0;
         int var5 = 0;
         int var6 = 0;
         eW.Sv var7 = eW.Sv.pC;

         while (!var3.isEmpty()) {
            int var8 = (Integer)var3.remove();
            if (var2.add(var8)) {
               com.pnfsoftware.jeb.corei.parsers.ethereum.Av var9 = (com.pnfsoftware.jeb.corei.parsers.ethereum.Av)ma.this.E.get(var8);
               if (var9 == null) {
                  if (var8 != ma.this.A) {
                     throw new RuntimeException(Strings.ff("No block at offset 0x%X", var8));
                  }
               } else {
                  if (var7 != eW.Sv.kS) {
                     for (Mh var11 : ma.this.pC(var9)) {
                        eW.Sv var12 = var11.gp();
                        if (var12.compareTo(var7) > 0) {
                           var7 = var12;
                           if (var12 == eW.Sv.kS) {
                              break;
                           }
                        }
                     }
                  }

                  if (var9.pC()) {
                     var6++;
                  } else {
                     if (var9.fI) {
                        var4++;
                     }

                     if (var9.ys) {
                        var5++;
                     }
                  }

                  if (var9.UT != null) {
                     var3.add(var9.UT);
                  }

                  if (var9.oT) {
                     if (var9.wS != null) {
                        eW var14 = (eW)ma.this.gp.get(var9.wS);
                        var1.sY.add(var14);
                        var14.E.add(var1);
                     }

                     var3.addAll(var9.E());
                  } else if (var9.wS != null) {
                     var3.add(var9.wS);
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

         var1.UT = var6 == 0 ? var13 : -var13;
         var1.wS = var7;
      }

      void A(eW var1) {
         if (this.A.add(var1)) {
            eW.Sv var2 = var1.wS;

            for (int var3 = 0; var3 < this.kS.size(); var3++) {
               eW.Sv var4 = (eW.Sv)this.kS.get(var3);
               if (var4.compareTo(var2) < 0) {
                  this.kS.set(var3, var2);
               }
            }

            this.kS.add(var2);

            for (eW var7 : var1.sY) {
               this.A(var7);
            }

            eW.Sv var6 = (eW.Sv)this.kS.remove(this.kS.size() - 1);
            if (var1.wS != var6) {
               var1.wS = var6;
               this.pC++;
            }
         }
      }
   }

   public static class Sv {
      boolean pC;
      Integer A;
      Integer kS;

      Sv(boolean var1) {
         this.pC = var1;
      }
   }
}
