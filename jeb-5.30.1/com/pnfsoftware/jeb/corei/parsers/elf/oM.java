package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.codeobject.IELFDynamicTable;
import com.pnfsoftware.jeb.util.io.ChannelUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jebglobal.cvm;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Ser
public class oM implements IELFDynamicTable {
   private static final ILogger KT = GlobalLog.getLogger(oM.class);
   @SerId(1)
   private vb Gf;
   @SerId(2)
   private boolean Ef;
   @SerId(3)
   private long cC;
   @SerId(4)
   private long sH;
   @SerId(5)
   private long CE;
   @SerId(6)
   private long wF;
   @SerId(7)
   private long If;
   @SerId(8)
   private long Dz;
   @SerId(9)
   private long mI;
   @SerId(10)
   private int jq;
   @SerId(11)
   private int ui;
   @SerId(12)
   private int TX;
   @SerId(13)
   private List Rr = new ArrayList();
   @SerId(14)
   private long EB;
   @SerId(15)
   private long Xo;
   @SerId(16)
   private long Bu;
   @SerId(17)
   private long IN;
   @SerId(18)
   List q;
   @SerId(19)
   List RF;
   @SerId(20)
   String xK;
   @SerId(21)
   String Dw;
   @SerId(22)
   List Uv;
   @SerId(23)
   private long rL;
   @SerId(24)
   private long eJ;
   @SerId(25)
   List oW;
   @SerId(26)
   boolean gO;
   @SerTransient
   long nf;
   @SerTransient
   int gP;
   @SerTransient
   int za;
   @SerTransient
   CI lm;
   @SerTransient
   long zz;
   @SerTransient
   int JY;
   @SerTransient
   int HF;
   @SerTransient
   CI LK;
   @SerTransient
   long io;
   @SerTransient
   long qa;
   @SerTransient
   int Hk;
   @SerTransient
   int Me;
   @SerTransient
   long PV;
   @SerTransient
   qa oQ;
   @SerTransient
   CI xW;

   public oM(vb var1, ej var2, boolean var3) {
      this.Gf = var1;
      this.Ef = var3;

      for (nI var5 : var2.q) {
         switch ((int)var5.q) {
            case 0:
            case 21:
               break;
            case 1:
               this.Rr.add((int)var5.RF);
               break;
            case 2:
               this.Bu = var5.RF;
               break;
            case 3:
               this.EB = var1.RF(var5.RF);
               break;
            case 4:
               this.io = var1.RF(var5.RF);
               break;
            case 5:
               this.mI = var1.RF(var5.RF);
               break;
            case 6:
               this.PV = var1.RF(var5.RF);
               break;
            case 7:
               this.nf = var1.RF(var5.RF);
               break;
            case 8:
               this.gP = (int)var5.RF;
               break;
            case 9:
               this.za = (int)var5.RF;
               break;
            case 10:
               this.jq = (int)var5.RF;
               break;
            case 11:
               this.Hk = (int)var5.RF;
               break;
            case 12:
               this.cC = var5.RF;
               break;
            case 13:
               this.sH = var5.RF;
               break;
            case 14:
               this.ui = (int)var5.RF;
               break;
            case 15:
            case 29:
               this.TX = (int)var5.RF;
               break;
            case 17:
               this.zz = var1.RF(var5.RF);
               break;
            case 18:
               this.JY = (int)var5.RF;
               break;
            case 19:
               this.HF = (int)var5.RF;
               break;
            case 20:
               this.IN = var5.RF;
               break;
            case 22:
               this.gO = true;
               break;
            case 23:
               this.Xo = var1.RF(var5.RF);
               break;
            case 25:
               this.CE = var5.RF;
               break;
            case 26:
               this.If = var5.RF;
               break;
            case 27:
               this.wF = var5.RF;
               break;
            case 28:
               this.Dz = var5.RF;
               break;
            case 32:
               this.rL = var5.RF;
               break;
            case 33:
               this.eJ = var5.RF;
               break;
            case 34:
               this.Me = (int)var5.RF;
               break;
            case 1879047925:
               this.qa = var1.RF(var5.RF);
               break;
            default:
               Object[] var10000 = new Object[]{var5, var5.q};
         }
      }
   }

   void q(SeekableByteChannel var1, boolean var2, ME var3, vX var4) throws IOException {
      this.oW = new ArrayList();
      if (this.rL != 0L && this.eJ > 0L) {
         long var5 = this.Gf.RF(this.rL);
         ByteBuffer var7 = ChannelUtil.read(var1, var5, (int)this.eJ, var2);

         while (var7.remaining() > 0) {
            if (!q(var7.remaining(), this.Ef)) {
               KT.debug("ELF dyntab: Not enough bytes to read an address: %d", var7.remaining());
               break;
            }

            long var8 = this.Ef ? var7.getLong() : var7.getInt() & 4294967295L;
            if (var8 != 0L && (!this.Ef || var8 != -1L) && (this.Ef || var8 != 4294967295L)) {
               this.oW.add(var8);
            }
         }
      }

      this.q = new ArrayList();
      if (this.cC != 0L) {
         this.q.add(this.cC);
      }

      if (this.CE != 0L && this.wF > 0L) {
         long var10 = this.Gf.RF(this.CE);
         if (var10 == -1L) {
            KT.debug("ELF dyntab: Cannot read initializers array at memory address 0x%X", this.CE);
         } else {
            ByteBuffer var15 = ChannelUtil.read(var1, var10, (int)this.wF, var2);

            while (var15.remaining() > 0) {
               if (!q(var15.remaining(), this.Ef)) {
                  KT.debug("ELF dyntab: Not enough bytes to read an address: %d", var15.remaining());
                  break;
               }

               long var25 = this.Ef ? var15.getLong() : var15.getInt() & 4294967295L;
               if (var25 != 0L && (!this.Ef || var25 != -1L) && (this.Ef || var25 != 4294967295L)) {
                  this.q.add(var25);
               }
            }
         }
      }

      this.RF = new ArrayList();
      if (this.sH != 0L) {
         this.RF.add(this.sH);
      }

      if (this.If != 0L && this.Dz > 0L) {
         long var11 = this.Gf.RF(this.If);
         if (var11 == -1L) {
            KT.debug("ELF dyntab: Cannot read finalizers array at memory address 0x%X", this.If);
         } else {
            ByteBuffer var16 = ChannelUtil.read(var1, var11, (int)this.Dz, var2);

            while (var16.remaining() > 0) {
               if (!q(var16.remaining(), this.Ef)) {
                  KT.debug("ELF dyntab: Not enough bytes to read an address: %d", var16.remaining());
                  break;
               }

               long var26 = this.Ef ? var16.getLong() : var16.getInt() & 4294967295L;
               if (var26 != 0L && (!this.Ef || var26 != -1L) && (this.Ef || var26 != 4294967295L)) {
                  this.RF.add(var26);
               }
            }
         }
      }

      HA var12 = null;
      if (this.mI != 0L && this.jq != 0) {
         ByteBuffer var6 = ChannelUtil.read(var1, this.mI, this.jq, var2);
         var12 = new HA(Arrays.copyOf(var6.array(), var6.limit()));
      }

      this.Uv = new ArrayList();
      if (var12 != null) {
         if (this.ui != 0) {
            this.xK = var12.q(this.ui);
         }

         if (this.TX != 0) {
            this.Dw = var12.q(this.TX);
         }

         for (int var17 : this.Rr) {
            this.Uv.add(var12.q(var17));
         }
      }

      int var14 = 0;
      if (this.io != 0L) {
         ByteBuffer var18 = ChannelUtil.read(var1, this.io, 8, var2);
         var14 = var18.getInt(4);
      } else if (this.qa != 0L) {
         ByteBuffer var19 = ChannelUtil.read(var1, this.qa, 32, var2);
         Bu var27 = com.pnfsoftware.jeb.corei.parsers.elf.Bu.q(var19, this.qa);
         var14 = var27.q(var1, this.Ef, var2);
      }

      if (this.PV != 0L && !var3.q(this.PV)) {
         if (var14 > 0 && this.Gf.getName().contains(cvm.q(new byte[]{-56, 49, 0, 35, 38, 28, 29}, 1, 137))) {
            int var20 = (int)((this.mI - this.PV) / this.Hk);
            if (var20 > var14 && var20 - var14 <= 10) {
               var14 = var20;
            }
         }

         ByteBuffer var21 = ChannelUtil.read(var1, this.PV, var14 * this.Hk, var2);
         this.oQ = com.pnfsoftware.jeb.corei.parsers.elf.qa.q(var21, this.Ef);
         this.oQ.Dw = this.PV;
         this.oQ.RF = this.Me;
         this.oQ.xK = true;
         if (var12 != null) {
            for (LR var9 : this.oQ.q) {
               var9.q(var12.q(var9.q));
            }
         }

         var3.q(this.oQ, true);
      }

      if (this.Xo != 0L && this.Bu > 0L && !var4.q(this.Xo)) {
         if (this.IN != 17L && this.IN != 7L) {
            throw new RuntimeException("Expected a PLT relocation table type");
         }

         ByteBuffer var22 = ChannelUtil.read(var1, this.Xo, (int)this.Bu, var2);
         this.xW = CI.q(var22, this.Ef, this.IN == 7L);
         this.xW.Uv = this.Xo;
         var4.q(this.xW);
      }

      if (this.nf != 0L && this.gP > 0 && this.za > 0 && !var4.q(this.nf)) {
         ByteBuffer var23 = ChannelUtil.read(var1, this.nf, this.gP, var2);
         this.lm = CI.q(var23, this.Ef, true);
         this.lm.Uv = this.nf;
         var4.q(this.lm);
      }

      if (this.zz != 0L && this.JY > 0 && this.HF > 0 && !var4.q(this.zz)) {
         ByteBuffer var24 = ChannelUtil.read(var1, this.zz, this.JY, var2);
         this.LK = CI.q(var24, this.Ef, false);
         this.LK.Uv = this.zz;
         var4.q(this.LK);
      }
   }

   private static boolean q(int var0, boolean var1) {
      return var1 && var0 >= 8 || !var1 && var0 >= 4;
   }

   public boolean q() {
      return this.gO;
   }

   @Override
   public List getPreInitializers() {
      return this.oW;
   }

   @Override
   public List getInitializers() {
      return this.q;
   }

   public List q(long var1, IVirtualMemory var3) {
      ArrayList var4 = new ArrayList();
      if (this.CE != 0L && this.wF > 0L) {
         long var5 = var1 + this.CE;
         long var7 = var5 + this.wF;

         while (var5 < var7) {
            long var9;
            if (this.Ef) {
               try {
                  var9 = var3.readLong(var5);
               } catch (MemoryException var11) {
                  break;
               }

               var5 += 8L;
            } else {
               try {
                  var9 = var3.readInt(var5) & 4294967295L;
               } catch (MemoryException var12) {
                  break;
               }

               var5 += 4L;
            }

            if (var9 != 0L && (!this.Ef || var9 != -1L) && (this.Ef || var9 != 4294967295L)) {
               var4.add(var9);
            }
         }
      }

      return var4;
   }

   @Override
   public List getFinalizers() {
      return this.RF;
   }

   @Override
   public String getLibraryName() {
      return this.xK;
   }

   @Override
   public String getSearchPath() {
      return this.Dw;
   }

   @Override
   public List getRequiredLibs() {
      return this.Uv;
   }
}
