package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.core.units.codeobject.IELFDynamicTable;
import com.pnfsoftware.jeb.util.io.ChannelUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jebglobal.ckx;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Ser
public class bO implements IELFDynamicTable {
   private static final ILogger rl = GlobalLog.getLogger(bO.class);
   @SerId(1)
   private sy z;
   @SerId(2)
   private boolean Ek;
   @SerId(3)
   private long hK;
   @SerId(4)
   private long Er;
   @SerId(5)
   private long FE;
   @SerId(6)
   private long Aj;
   @SerId(7)
   private long EX;
   @SerId(8)
   private long LM;
   @SerId(9)
   private long mv;
   @SerId(10)
   private int sO;
   @SerId(11)
   private int os;
   @SerId(12)
   private int Cu;
   @SerId(13)
   private List hZ = new ArrayList();
   @SerId(14)
   private long UW;
   @SerId(15)
   private long PR;
   @SerId(16)
   private long cX;
   @SerId(17)
   private long DQ;
   @SerId(18)
   List pC;
   @SerId(19)
   List A;
   @SerId(20)
   String kS;
   @SerId(21)
   String wS;
   @SerId(22)
   List UT;
   @SerId(23)
   private long ZN;
   @SerId(24)
   private long OB;
   @SerId(25)
   List E;
   @SerId(26)
   boolean sY;
   @SerTransient
   long ys;
   @SerTransient
   int ld;
   @SerTransient
   int gp;
   @SerTransient
   ma oT;
   @SerTransient
   long fI;
   @SerTransient
   int WR;
   @SerTransient
   int NS;
   @SerTransient
   ma vP;
   @SerTransient
   long xC;
   @SerTransient
   long ED;
   @SerTransient
   int Sc;
   @SerTransient
   int ah;
   @SerTransient
   long eP;
   @SerTransient
   m UO;
   @SerTransient
   ma Ab;

   public bO(sy var1, Ws var2, boolean var3) {
      this.z = var1;
      this.Ek = var3;

      for (K var5 : var2.pC) {
         switch ((int)var5.pC) {
            case 0:
            case 21:
               break;
            case 1:
               this.hZ.add((int)var5.A);
               break;
            case 2:
               this.cX = var5.A;
               break;
            case 3:
               this.UW = var1.pC(var5.A);
               break;
            case 4:
               this.xC = var1.pC(var5.A);
               break;
            case 5:
               this.mv = var1.pC(var5.A);
               break;
            case 6:
               this.eP = var1.pC(var5.A);
               break;
            case 7:
               this.ys = var1.pC(var5.A);
               break;
            case 8:
               this.ld = (int)var5.A;
               break;
            case 9:
               this.gp = (int)var5.A;
               break;
            case 10:
               this.sO = (int)var5.A;
               break;
            case 11:
               this.Sc = (int)var5.A;
               break;
            case 12:
               this.hK = var5.A;
               break;
            case 13:
               this.Er = var5.A;
               break;
            case 14:
               this.os = (int)var5.A;
               break;
            case 15:
            case 29:
               this.Cu = (int)var5.A;
               break;
            case 17:
               this.fI = var1.pC(var5.A);
               break;
            case 18:
               this.WR = (int)var5.A;
               break;
            case 19:
               this.NS = (int)var5.A;
               break;
            case 20:
               this.DQ = var5.A;
               break;
            case 22:
               this.sY = true;
               break;
            case 23:
               this.PR = var1.pC(var5.A);
               break;
            case 25:
               this.FE = var5.A;
               break;
            case 26:
               this.EX = var5.A;
               break;
            case 27:
               this.Aj = var5.A;
               break;
            case 28:
               this.LM = var5.A;
               break;
            case 32:
               this.ZN = var5.A;
               break;
            case 33:
               this.OB = var5.A;
               break;
            case 34:
               this.ah = (int)var5.A;
               break;
            case 1879047925:
               this.ED = var1.pC(var5.A);
               break;
            default:
               Object[] var10000 = new Object[]{var5, var5.pC};
         }
      }
   }

   void pC(SeekableByteChannel var1, boolean var2, eW var3, gJ var4) throws IOException {
      this.E = new ArrayList();
      if (this.ZN != 0L && this.OB > 0L) {
         long var5 = this.z.pC(this.ZN);
         ByteBuffer var7 = ChannelUtil.read(var1, var5, (int)this.OB, var2);

         while (var7.remaining() > 0) {
            if (!pC(var7.remaining(), this.Ek)) {
               rl.debug("ELF dyntab: Not enough bytes to read an address: %d", var7.remaining());
               break;
            }

            long var8 = this.Ek ? var7.getLong() : var7.getInt() & 4294967295L;
            if (var8 != 0L && (!this.Ek || var8 != -1L) && (this.Ek || var8 != 4294967295L)) {
               this.E.add(var8);
            }
         }
      }

      this.pC = new ArrayList();
      if (this.hK != 0L) {
         this.pC.add(this.hK);
      }

      if (this.FE != 0L && this.Aj > 0L) {
         long var10 = this.z.pC(this.FE);
         if (var10 == -1L) {
            rl.debug("ELF dyntab: Cannot read initializers array at memory address 0x%X", this.FE);
         } else {
            ByteBuffer var15 = ChannelUtil.read(var1, var10, (int)this.Aj, var2);

            while (var15.remaining() > 0) {
               if (!pC(var15.remaining(), this.Ek)) {
                  rl.debug("ELF dyntab: Not enough bytes to read an address: %d", var15.remaining());
                  break;
               }

               long var25 = this.Ek ? var15.getLong() : var15.getInt() & 4294967295L;
               if (var25 != 0L && (!this.Ek || var25 != -1L) && (this.Ek || var25 != 4294967295L)) {
                  this.pC.add(var25);
               }
            }
         }
      }

      this.A = new ArrayList();
      if (this.Er != 0L) {
         this.A.add(this.Er);
      }

      if (this.EX != 0L && this.LM > 0L) {
         long var11 = this.z.pC(this.EX);
         if (var11 == -1L) {
            rl.debug("ELF dyntab: Cannot read finalizers array at memory address 0x%X", this.EX);
         } else {
            ByteBuffer var16 = ChannelUtil.read(var1, var11, (int)this.LM, var2);

            while (var16.remaining() > 0) {
               if (!pC(var16.remaining(), this.Ek)) {
                  rl.debug("ELF dyntab: Not enough bytes to read an address: %d", var16.remaining());
                  break;
               }

               long var26 = this.Ek ? var16.getLong() : var16.getInt() & 4294967295L;
               if (var26 != 0L && (!this.Ek || var26 != -1L) && (this.Ek || var26 != 4294967295L)) {
                  this.A.add(var26);
               }
            }
         }
      }

      Kr var12 = null;
      if (this.mv != 0L && this.sO != 0) {
         ByteBuffer var6 = ChannelUtil.read(var1, this.mv, this.sO, var2);
         var12 = new Kr(Arrays.copyOf(var6.array(), var6.limit()));
      }

      this.UT = new ArrayList();
      if (var12 != null) {
         if (this.os != 0) {
            this.kS = var12.pC(this.os);
         }

         if (this.Cu != 0) {
            this.wS = var12.pC(this.Cu);
         }

         for (int var17 : this.hZ) {
            this.UT.add(var12.pC(var17));
         }
      }

      int var14 = 0;
      if (this.xC != 0L) {
         ByteBuffer var18 = ChannelUtil.read(var1, this.xC, 8, var2);
         var14 = var18.getInt(4);
      } else if (this.ED != 0L) {
         ByteBuffer var19 = ChannelUtil.read(var1, this.ED, 32, var2);
         oP var27 = oP.pC(var19, this.ED);
         var14 = var27.pC(var1, this.Ek, var2);
      }

      if (this.eP != 0L && !var3.pC(this.eP)) {
         if (var14 > 0 && this.z.getName().contains(ckx.pC(new byte[]{2, 31, 0, 42, 7, 0, 19}, 2, 15))) {
            int var20 = (int)((this.mv - this.eP) / this.Sc);
            if (var20 > var14 && var20 - var14 <= 10) {
               var14 = var20;
            }
         }

         ByteBuffer var21 = ChannelUtil.read(var1, this.eP, var14 * this.Sc, var2);
         this.UO = m.pC(var21, this.Ek);
         this.UO.wS = this.eP;
         this.UO.A = this.ah;
         this.UO.kS = true;
         if (var12 != null) {
            for (nA var9 : this.UO.pC) {
               var9.pC(var12.pC(var9.pC));
            }
         }

         var3.pC(this.UO, true);
      }

      if (this.PR != 0L && this.cX > 0L && !var4.pC(this.PR)) {
         if (this.DQ != 17L && this.DQ != 7L) {
            throw new RuntimeException("Expected a PLT relocation table type");
         }

         ByteBuffer var22 = ChannelUtil.read(var1, this.PR, (int)this.cX, var2);
         this.Ab = ma.pC(var22, this.Ek, this.DQ == 7L);
         this.Ab.UT = this.PR;
         var4.pC(this.Ab);
      }

      if (this.ys != 0L && this.ld > 0 && this.gp > 0 && !var4.pC(this.ys)) {
         ByteBuffer var23 = ChannelUtil.read(var1, this.ys, this.ld, var2);
         this.oT = ma.pC(var23, this.Ek, true);
         this.oT.UT = this.ys;
         var4.pC(this.oT);
      }

      if (this.fI != 0L && this.WR > 0 && this.NS > 0 && !var4.pC(this.fI)) {
         ByteBuffer var24 = ChannelUtil.read(var1, this.fI, this.WR, var2);
         this.vP = ma.pC(var24, this.Ek, false);
         this.vP.UT = this.fI;
         var4.pC(this.vP);
      }
   }

   private static boolean pC(int var0, boolean var1) {
      return var1 && var0 >= 8 || !var1 && var0 >= 4;
   }

   public boolean pC() {
      return this.sY;
   }

   @Override
   public List getPreInitializers() {
      return this.E;
   }

   @Override
   public List getInitializers() {
      return this.pC;
   }

   @Override
   public List getFinalizers() {
      return this.A;
   }

   @Override
   public String getLibraryName() {
      return this.kS;
   }

   @Override
   public String getSearchPath() {
      return this.wS;
   }

   @Override
   public List getRequiredLibs() {
      return this.UT;
   }
}
