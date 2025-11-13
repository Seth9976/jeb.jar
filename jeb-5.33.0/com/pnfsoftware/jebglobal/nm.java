package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ISegmentInformation;
import com.pnfsoftware.jeb.util.collect.LongSegment;
import com.pnfsoftware.jeb.util.collect.SegmentMap;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.io.Endianness;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class nm extends kx {
   private SegmentMap ys;
   private final CC ld;

   public nm(a var1) {
      super(var1);
      this.pC = "data-string";
      this.ld = new CC(var1);
   }

   protected INativeDataItem kS(long var1, long var3, boolean var5) {
      if (this.wS.getStandardEndianess() == Endianness.LITTLE_ENDIAN) {
         try {
            if (this.wS.readByte(var1) == 0) {
               return null;
            }
         } catch (MemoryException var6) {
            return null;
         }
      }

      return this.kS(var1, var3);
   }

   @Override
   protected List A(long var1, long var3, boolean var5) {
      INativeDataItem var6 = this.kS(var1, var3, var5);
      if (var6 != null) {
         ArrayList var7 = new ArrayList();
         var7.add(var6);
         return var7;
      } else {
         return Collections.emptyList();
      }
   }

   private INativeDataItem kS(long var1, long var3) {
      avb var6;
      try (ACLock var5 = this.kS.pC.a()) {
         if (this.kS(var1) != null) {
            return this.A.pC(var1, var3, null, 0, 0, true);
         }

         var6 = this.ld.A(var1, var3);
      }

      return var6;
   }

   @Override
   protected long pC(long var1) {
      long var3 = this.UT();
      LongSegment var5 = this.kS(var3);
      return var5 == null ? super.pC(var1) : Math.min(var1, var5.getEnd());
   }

   @Override
   protected boolean wS() {
      long var1 = this.UT();
      long var3 = this.E();
      int var5 = 16 - (int)(var1 & 15L);
      int var6 = 0;

      try {
         while (var6 < var5 && var1 < var3 && this.wS.readByte(var1) == 0) {
            var1++;
            var6++;
         }

         if (var6 > 0) {
            this.A(var1);
         }
      } catch (MemoryException var7) {
      }

      return var6 > 0;
   }

   private LongSegment kS(long var1) {
      if (this.ys == null) {
         this.ys = new SegmentMap();
         ICodeObjectUnit var3 = this.A.getContainer();
         if (var3 instanceof IELFUnit) {
            for (ISegmentInformation var6 : this.A.getContainer().getValidSections()) {
               if (var6.getOffsetInMemory() != 0L && var6.getSizeInMemory() != 0L) {
                  String var7 = var6.getName();
                  if (var3 instanceof IELFUnit && var7.equals(".dynstr")) {
                     LongSegment var8 = new LongSegment(this.ld().getVirtualImageBase() + var6.getOffsetInMemory(), var6.getSizeInMemory());
                     this.ys.add(var8);
                  }
               }
            }
         }
      }

      return (LongSegment)this.ys.getSegmentContaining(var1);
   }

   private INativeCodeUnit ld() {
      return this.A.wS().A();
   }
}
