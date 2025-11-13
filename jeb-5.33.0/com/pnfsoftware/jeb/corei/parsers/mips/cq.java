package com.pnfsoftware.jeb.corei.parsers.mips;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.codeobject.ELFRelocationApplicator;
import com.pnfsoftware.jeb.core.units.codeobject.ELFRelocationContext;
import com.pnfsoftware.jeb.core.units.codeobject.ELFStandardRelocOperations;
import com.pnfsoftware.jeb.core.units.codeobject.IELFArchRelocatorFacility;
import com.pnfsoftware.jeb.core.units.codeobject.IELFRelocationApplicator;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.math.MathUtil;
import java.util.Arrays;
import java.util.List;

public class cq implements IELFArchRelocatorFacility {
   public static cq.Sv pC = new DH(0);
   public static cq.Sv A = new KD(3);
   public static cq.Sv kS = new yt(2);
   public static cq.Sv wS = new RC(5);
   public static cq.Sv UT = new sy(6);
   public static cq.Sv E = new HE(4);
   public static cq.Sv sY = new qt(11);
   public static cq.Sv ys = new oP(37);
   public static cq.Sv ld = new vi(9);
   public static ELFRelocationContext gp = new rQ(pC, A, kS, wS, UT, E, sY, ys, ld);
   public static ELFRelocationContext oT = new zp(pC, A);

   protected static short pC(long var0) {
      return (short)(var0 - (short)var0 >> 16);
   }

   protected static void pC(cq.Av var0, short var1) throws MemoryException {
      if (var0.endianness.isBig()) {
         var0.mem.writeShort(var0.P() + 2L, var1);
      } else {
         var0.mem.writeShort(var0.P(), var1);
      }
   }

   protected static void pC(cq.Av var0, int var1) throws MemoryException {
      int var2 = var0.mem.readInt(var0.P());
      var0.mem.writeInt(var0.P(), var2 & -67108864 | var1 & 67108863);
   }

   @Override
   public List getRelocationContexts() {
      return Arrays.asList(gp, oT);
   }

   static class Av extends ELFStandardRelocOperations {
      Av(IELFRelocationApplicator.RelocatedFile var1, IELFRelocationApplicator.RelocInstance var2) {
         super(var1, var2);
      }

      @Override
      public long A() throws MemoryException {
         if (this.relocEntry.isAddendSet()) {
            return this.relocEntry.getAddend();
         } else if (this.relocEntry.getType() != 4) {
            return this.mem.readInt(this.targetAddress) & 4294967295L;
         } else {
            long var1 = (this.mem.readInt(this.targetAddress) & 67108863) << 2;
            if (this.se.getType() != 3 || this.se.getBinding() != 0) {
               var1 = MathUtil.signExtend(var1, 28);
            }

            return var1;
         }
      }

      public long pC() throws MemoryException {
         if (this.relocEntry.isAddendSet()) {
            return this.relocEntry.getAddend();
         } else if (this.relocEntry.getType() == 5) {
            if (this.kS() != 0) {
               throw new com.pnfsoftware.jeb.corei.parsers.elf.yt(Strings.ff("MIPS reloc HI16 AHL != 0 (0x%x)", this.targetAddress));
            } else {
               return 0L;
            }
         } else {
            return this.relocEntry.getType() == 6 ? this.kS() : 0L;
         }
      }

      private short kS() throws MemoryException {
         short var1;
         if (this.endianness.isBig()) {
            var1 = this.mem.readShort(this.targetAddress + 2L);
         } else {
            var1 = this.mem.readShort(this.targetAddress);
         }

         return var1;
      }
   }

   abstract static class Sv extends ELFRelocationApplicator {
      public Sv(int var1) {
         super(var1);
      }

      @Override
      public void apply(IELFRelocationApplicator.RelocatedFile var1, IELFRelocationApplicator.RelocInstance var2) throws MemoryException {
         cq.Av var3 = new cq.Av(var1, var2);
         this.applyInternal(var3);
      }
   }
}
