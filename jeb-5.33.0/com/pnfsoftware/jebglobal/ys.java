package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.AddressConversionPrecision;
import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.AbstractMetadataGroup;
import com.pnfsoftware.jeb.core.units.MetadataGroupType;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.codeobject.CodeObjectUnitUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;
import java.util.Map;

@Ser
public class ys extends AbstractMetadataGroup {
   @SerId(1)
   C pC;

   public ys(C var1) {
      super("primary", MetadataGroupType.CLASSID);
      this.pC = var1;
   }

   @Override
   public Map getAllData() {
      return null;
   }

   @Override
   public Object getData(String var1) {
      return this.getData(var1, AddressConversionPrecision.DEFAULT);
   }

   @Override
   public Object getData(String var1, AddressConversionPrecision var2) {
      long var3 = this.pC.getCanonicalMemoryAddress(var1, var2);
      return this.pC(var3, var2);
   }

   public Object pC(long var1, AddressConversionPrecision var3) {
      ItemClassIdentifiers var4 = ItemClassIdentifiers.CODE_UNKNOWN;
      if (var1 != -1L) {
         INativeContinuousItem var5 = this.pC.getCodeModel().getItemOver(var1);
         if (var5 instanceof aus) {
            var4 = ItemClassIdentifiers.CODE_INSTRUCTIONS;
            List var13 = this.pC.getCodeModel().getContainedRoutineAddresses(var5.getMemoryAddress());
            if (var13 == null || var13.isEmpty()) {
               var4 = ItemClassIdentifiers.CODE_ORPHAN;
            } else if (this.pC(var13)) {
               var4 = ItemClassIdentifiers.CODE_LIBRARY;
            } else if (this.A(var13)) {
               var4 = ItemClassIdentifiers.CODE_STUB;
            }
         } else if (var5 instanceof INativeDataItem && !(var5 instanceof auxx)) {
            var4 = ItemClassIdentifiers.CODE_DATA;
         } else if (var5 == null && var3 == AddressConversionPrecision.COARSE) {
            INativeContinuousItem var6 = this.pC.getCodeModel().getPreviousItem(var1);
            long var7 = -1L;
            if (var6 != null) {
               var7 = var1 - var6.getMemoryAddressEnd();
               if (var7 <= 1L) {
                  return this.pC(var6.getMemoryAddress(), var3);
               }
            }

            INativeContinuousItem var9 = this.pC.getCodeModel().getNextItem(var1);
            long var10 = -1L;
            if (var9 != null) {
               var10 = var9.getMemoryAddress() - var1;
               if (var10 <= 1L) {
                  return this.pC(var9.getMemoryAddress(), var3);
               }
            }

            Boolean var12 = null;
            if ((var9 == null || var7 <= var10) && var6 != null) {
               var12 = Boolean.TRUE;
            } else if ((var6 == null || var7 > var10) && var9 != null) {
               var12 = Boolean.FALSE;
            }

            if (var12 != null) {
               if (var12) {
                  if (var12 && this.pC(var7, var6.getMemoryAddressEnd())) {
                     return this.pC(var6.getMemoryAddress(), var3);
                  }
               } else if (!var12 && this.pC(var10, var1)) {
                  return this.pC(var9.getMemoryAddress(), var3);
               }
            }
         }

         if (var4 == ItemClassIdentifiers.CODE_UNKNOWN) {
            ICodeObjectUnit var14 = this.pC.getCodeObjectContainer();
            if (var14 != null
               && CodeObjectUnitUtil.findSectionByRelativeAddress(var14, var1 - this.pC.getVirtualImageBase()) == null
               && CodeObjectUnitUtil.findSegmentByRelativeAddress(var14, var1 - this.pC.getVirtualImageBase()) == null) {
               var4 = ItemClassIdentifiers.CODE_SLACK;
            }
         }
      }

      return var4.getId();
   }

   // $VF: Handled exception range with multiple entry points by splitting it
   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   private boolean pC(long var1, long var3) {
      if (var1 <= 4L) {
         return true;
      } else if (var1 > 16L) {
         return false;
      } else {
         byte[] var5;
         try {
            var5 = new byte[(int)var1];
            int var6 = this.pC.getMemory().read(var3, (int)var1, var5, 0);
            if (var6 < var1) {
               return false;
            }
         } catch (MemoryException var10) {
            return false;
         }

         int var7;
         try {
            var7 = 1;
         } catch (MemoryException var8) {
            return false;
         }

         while (true) {
            try {
               if (var7 >= var1) {
                  return true;
               }

               if (var5[var7] != var5[0]) {
                  return false;
               }
            } catch (MemoryException var9) {
               return false;
            }

            var7++;
         }
      }
   }

   private boolean pC(List var1) {
      if (var1 != null) {
         for (Long var3 : var1) {
            auu var4 = this.pC.A(var3);
            if (var4 != null && (var4.z() != null || var4.hK() != null)) {
               return true;
            }
         }
      }

      return false;
   }

   private boolean A(List var1) {
      if (var1 != null) {
         for (Long var3 : var1) {
            auu var4 = this.pC.A(var3);
            if (var4 != null && var4.E().E() != null) {
               return true;
            }
         }
      }

      return false;
   }

   @Override
   public boolean setData(String var1, Object var2) {
      return false;
   }

   @Override
   public List getSectionAnchorIds() {
      return null;
   }
}
