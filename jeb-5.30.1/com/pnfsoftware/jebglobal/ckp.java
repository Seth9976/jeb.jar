package com.pnfsoftware.jebglobal;

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

public class ckp implements IELFArchRelocatorFacility {
   public static ckp.CU q = new ckq(0);
   public static ckp.CU RF = new ckt(3);
   public static ckp.CU xK = new cku(2);
   public static ckp.CU Dw = new ckv(5);
   public static ckp.CU Uv = new ckw(6);
   public static ckp.CU oW = new ckx(4);
   public static ckp.CU gO = new cky(11);
   public static ckp.CU nf = new ckz(37);
   public static ckp.CU gP = new cla(9);
   public static ELFRelocationContext za = new ckr(q, RF, xK, Dw, Uv, oW, gO, nf, gP);
   public static ELFRelocationContext lm = new cks(q, RF);

   protected static short q(long var0) {
      return (short)(var0 - (short)var0 >> 16);
   }

   protected static void q(ckp.eo var0, short var1) throws MemoryException {
      if (var0.endianness.isBig()) {
         var0.mem.writeShort(var0.P() + 2L, var1);
      } else {
         var0.mem.writeShort(var0.P(), var1);
      }
   }

   protected static void q(ckp.eo var0, int var1) throws MemoryException {
      int var2 = var0.mem.readInt(var0.P());
      var0.mem.writeInt(var0.P(), var2 & -67108864 | var1 & 67108863);
   }

   @Override
   public List getRelocationContexts() {
      return Arrays.asList(za, lm);
   }

   abstract static class CU extends ELFRelocationApplicator {
      public CU(int var1) {
         super(var1);
      }

      @Override
      public void apply(IELFRelocationApplicator.RelocatedFile var1, IELFRelocationApplicator.RelocInstance var2) throws MemoryException {
         ckp.eo var3 = new ckp.eo(var1, var2);
         this.applyInternal(var3);
      }
   }

   static class eo extends ELFStandardRelocOperations {
      static final int q = 67108863;

      eo(IELFRelocationApplicator.RelocatedFile var1, IELFRelocationApplicator.RelocInstance var2) {
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

      public long q() throws MemoryException {
         if (this.relocEntry.isAddendSet()) {
            return this.relocEntry.getAddend();
         } else if (this.relocEntry.getType() == 5) {
            if (this.RF() != 0) {
               throw new com.pnfsoftware.jeb.corei.parsers.elf.vn(Strings.ff("MIPS reloc HI16 AHL != 0 (0x%x)", this.targetAddress));
            } else {
               return 0L;
            }
         } else {
            return this.relocEntry.getType() == 6 ? this.RF() : 0L;
         }
      }

      private short RF() throws MemoryException {
         short var1;
         if (this.endianness.isBig()) {
            var1 = this.mem.readShort(this.targetAddress + 2L);
         } else {
            var1 = this.mem.readShort(this.targetAddress);
         }

         return var1;
      }
   }
}
