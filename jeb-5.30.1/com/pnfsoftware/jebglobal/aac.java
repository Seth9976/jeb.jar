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

public class aac extends PA {
   private SegmentMap nf;
   private final aal gP;

   public aac(aae var1) {
      super(var1);
      this.q = "data-string";
      this.gP = new aal(var1);
   }

   protected INativeDataItem xK(long var1, long var3, boolean var5) {
      if (this.Dw.getStandardEndianess() == Endianness.LITTLE_ENDIAN) {
         try {
            if (this.Dw.readByte(var1) == 0) {
               return null;
            }
         } catch (MemoryException var6) {
            return null;
         }
      }

      return this.xK(var1, var3);
   }

   @Override
   protected List RF(long var1, long var3, boolean var5) {
      INativeDataItem var6 = this.xK(var1, var3, var5);
      if (var6 != null) {
         ArrayList var7 = new ArrayList();
         var7.add(var6);
         return var7;
      } else {
         return Collections.emptyList();
      }
   }

   private INativeDataItem xK(long var1, long var3) {
      axw var6;
      try (ACLock var5 = this.xK.q.a()) {
         if (this.Dw(var1) != null) {
            return this.RF.q(var1, var3, null, 0, 0, true);
         }

         var6 = this.gP.RF(var1, var3);
      }

      return var6;
   }

   @Override
   protected long q(long var1) {
      long var3 = this.Uv();
      LongSegment var5 = this.Dw(var3);
      return var5 == null ? super.q(var1) : Math.min(var1, var5.getEnd());
   }

   @Override
   protected boolean Dw() {
      long var1 = this.Uv();
      long var3 = this.oW();
      int var5 = 16 - (int)(var1 & 15L);
      int var6 = 0;

      try {
         while (var6 < var5 && var1 < var3 && this.Dw.readByte(var1) == 0) {
            var1++;
            var6++;
         }

         if (var6 > 0) {
            this.RF(var1);
         }
      } catch (MemoryException var7) {
      }

      return var6 > 0;
   }

   private LongSegment Dw(long var1) {
      if (this.nf == null) {
         this.nf = new SegmentMap();
         ICodeObjectUnit var3 = this.RF.getContainer();
         if (var3 instanceof IELFUnit) {
            for (ISegmentInformation var6 : this.RF.getContainer().getValidSections()) {
               if (var6.getOffsetInMemory() != 0L && var6.getSizeInMemory() != 0L) {
                  String var7 = var6.getName();
                  if (var3 instanceof IELFUnit && var7.equals(".dynstr")) {
                     LongSegment var8 = new LongSegment(this.gP().getVirtualImageBase() + var6.getOffsetInMemory(), var6.getSizeInMemory());
                     this.nf.add(var8);
                  }
               }
            }
         }
      }

      return (LongSegment)this.nf.getSegmentContaining(var1);
   }

   private INativeCodeUnit gP() {
      return this.RF.Dw().Dw();
   }
}
