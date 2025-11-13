package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import java.util.Set;

public class bao {
   public int pC = 0;
   public int A = 1;
   public long kS = 16L;
   public long wS = 64L;
   public long UT = 8L;
   public long E = 3L;
   public long sY = this.UT;
   public long ys = this.E;
   public long ld = 16L;
   public long gp = 4L;
   public int oT = 0;
   public final long fI = 0L;
   public final long WR = 1L;
   public final long NS = 2L;
   public final long vP = 3L;
   public long xC = 32L;
   public long ED = 4L;
   public long Sc = 0L;
   public long ah = 0L;
   public long eP = 0L;
   public long UO = 0L;
   public long Ab;
   public long rl;
   public long z;
   public long Ek = 3L;
   public long hK = 4L;
   public long Er = 3L;
   public long FE = 4L;
   public long Aj = 0L;
   public long EX = 1L;
   public long LM = 0L;
   public long mv = 1L;
   public long sO = 2L;
   public long os = 3L;
   public long Cu = 4L;
   public long hZ = 2L;
   public long UW = 3L;
   public long PR = 8L;
   public long cX = 4L;
   public long DQ = 8L / this.cX;
   public long ZN = this.cX * this.PR;
   boolean OB;
   boolean pF;
   boolean Bc;
   boolean OI;
   public long Bf;

   public boolean pC(int var1) {
      return var1 >= this.ah;
   }

   public bao(int var1, Set var2) {
      this.OB = var2.contains("ia32") || var2.contains("i386") || var2.contains("x86");
      this.Bc = var2.contains("arm-eabi") || var2.contains("arm");
      this.pF = var2.contains("x64-sysv") || var2.contains("x64-win") || var2.contains("x64");
      this.OI = var2.contains("arm64-sysv") || var2.contains("arm64");
      if (this.OB) {
         this.eP = 0L;
         this.UO = 0L;
         throw new ToDoException();
      } else {
         if (this.pF) {
            this.eP = 8L;
            this.UO = 22L;
         } else if (this.Bc) {
            this.eP = 0L;
            this.UO = 12L;
         } else {
            if (!this.OI) {
               throw new ToDoException();
            }

            this.eP = 8L;
            this.UO = 20L;
         }

         if (this.OB || this.Bc) {
            this.UT = 4L;
            this.E = 2L;
            this.ld = 8L;
            this.gp = 3L;
            this.DQ = 4L / this.cX;
         }

         if (!var2.contains("compressed-pointers")) {
            this.sY = this.UT;
            this.ys = this.E;
         } else {
            this.sY = 4L;
            this.ys = 2L;
         }

         if (var1 < 2190) {
            this.oT = 3;
            this.ah = 65536L;
            this.Bf = this.kS;
         } else {
            this.oT = 4;
            this.ah = 1048576L;
            this.Bf = this.wS;
         }

         if (var1 < 3020) {
            this.Ab = 0L;
            this.rl = 1L;
            this.z = 2L;
         } else {
            this.rl = 0L;
            this.Ab = 1L;
            this.z = 2L;
         }

         switch (var1) {
            case 2100:
               this.Sc = 97L;
               break;
            case 2120:
               this.Sc = 116L;
               break;
            case 2130:
               this.Sc = 119L;
               break;
            case 2140:
               this.Sc = 125L;
               break;
            case 2150:
               this.Sc = 127L;
               break;
            case 2160:
               this.Sc = 129L;
               break;
            case 2170:
               this.Sc = 131L;
               break;
            case 2180:
               this.Sc = 151L;
               break;
            case 2190:
               this.Sc = 160L;
               break;
            case 3000:
               this.Sc = 161L;
               break;
            case 3010:
               this.Sc = 164L;
               break;
            case 3020:
               this.Sc = 165L;
               break;
            case 3030:
               this.Sc = 165L;
               break;
            case 3040:
               this.Sc = 168L;
               break;
            case 3050:
               this.Sc = 166L;
               break;
            case 3060:
               this.Sc = 168L;
               break;
            case 3070:
               this.Sc = 169L;
               break;
            case 3080:
               this.Sc = 169L;
               break;
            default:
               this.Sc = 169L;
         }
      }
   }
}
