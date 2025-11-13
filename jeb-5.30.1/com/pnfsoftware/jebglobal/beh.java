package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import java.util.Set;

public class beh {
   public int q = 0;
   public int RF = 1;
   public long xK = 16L;
   public long Dw = 64L;
   public long Uv = 8L;
   public long oW = 3L;
   public long gO = this.Uv;
   public long nf = this.oW;
   public long gP = 16L;
   public long za = 4L;
   public int lm = 0;
   public final long zz = 0L;
   public final long JY = 1L;
   public final long HF = 2L;
   public final long LK = 3L;
   public long io = 32L;
   public long qa = 4L;
   public long Hk = 0L;
   public long Me = 0L;
   public long PV = 0L;
   public long oQ = 0L;
   public long xW;
   public long KT;
   public long Gf;
   public long Ef = 3L;
   public long cC = 4L;
   public long sH = 3L;
   public long CE = 4L;
   public long wF = 0L;
   public long If = 1L;
   public long Dz = 0L;
   public long mI = 1L;
   public long jq = 2L;
   public long ui = 3L;
   public long TX = 4L;
   public long Rr = 2L;
   public long EB = 3L;
   public long Xo = 8L;
   public long Bu = 4L;
   public long IN = 8L / this.Bu;
   public long rL = this.Bu * this.Xo;
   boolean eJ;
   boolean YN;
   boolean Rv;
   boolean zx;
   public long ZT;

   public boolean q(int var1) {
      return var1 >= this.Me;
   }

   public beh(int var1, Set var2) {
      this.eJ = var2.contains("ia32") || var2.contains("i386") || var2.contains("x86");
      this.Rv = var2.contains("arm-eabi") || var2.contains("arm");
      this.YN = var2.contains("x64-sysv") || var2.contains("x64-win") || var2.contains("x64");
      this.zx = var2.contains("arm64-sysv") || var2.contains("arm64");
      if (this.eJ) {
         this.PV = 0L;
         this.oQ = 0L;
         throw new ToDoException();
      } else {
         if (this.YN) {
            this.PV = 8L;
            this.oQ = 22L;
         } else if (this.Rv) {
            this.PV = 0L;
            this.oQ = 12L;
         } else {
            if (!this.zx) {
               throw new ToDoException();
            }

            this.PV = 8L;
            this.oQ = 20L;
         }

         if (this.eJ || this.Rv) {
            this.Uv = 4L;
            this.oW = 2L;
            this.gP = 8L;
            this.za = 3L;
            this.IN = 4L / this.Bu;
         }

         if (!var2.contains("compressed-pointers")) {
            this.gO = this.Uv;
            this.nf = this.oW;
         } else {
            this.gO = 4L;
            this.nf = 2L;
         }

         if (var1 < 2190) {
            this.lm = 3;
            this.Me = 65536L;
            this.ZT = this.xK;
         } else {
            this.lm = 4;
            this.Me = 1048576L;
            this.ZT = this.Dw;
         }

         if (var1 < 3020) {
            this.xW = 0L;
            this.KT = 1L;
            this.Gf = 2L;
         } else {
            this.KT = 0L;
            this.xW = 1L;
            this.Gf = 2L;
         }

         switch (var1) {
            case 2100:
               this.Hk = 97L;
               break;
            case 2120:
               this.Hk = 116L;
               break;
            case 2130:
               this.Hk = 119L;
               break;
            case 2140:
               this.Hk = 125L;
               break;
            case 2150:
               this.Hk = 127L;
               break;
            case 2160:
               this.Hk = 129L;
               break;
            case 2170:
               this.Hk = 131L;
               break;
            case 2180:
               this.Hk = 151L;
               break;
            case 2190:
               this.Hk = 160L;
               break;
            case 3000:
               this.Hk = 161L;
               break;
            case 3010:
               this.Hk = 164L;
               break;
            case 3020:
               this.Hk = 165L;
               break;
            case 3030:
               this.Hk = 165L;
               break;
            case 3040:
               this.Hk = 168L;
               break;
            case 3050:
               this.Hk = 166L;
               break;
            case 3060:
               this.Hk = 168L;
               break;
            case 3070:
               this.Hk = 169L;
               break;
            case 3080:
               this.Hk = 169L;
               break;
            default:
               this.Hk = 169L;
         }
      }
   }
}
