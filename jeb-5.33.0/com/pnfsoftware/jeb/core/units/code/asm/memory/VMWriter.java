package com.pnfsoftware.jeb.core.units.code.asm.memory;

import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrimitiveType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureTypeField;
import com.pnfsoftware.jeb.core.units.code.asm.type.PrimitiveCategory;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.io.EndianUtil;

public class VMWriter {
   IVirtualMemory vm;
   long addr;
   INativeType t;
   IStructureType ut;
   byte[] data;
   boolean bigend;

   public VMWriter(IVirtualMemory var1, long var2, INativeType var4) {
      this.vm = var1;
      this.addr = var2;
      this.t = var4;
      INativeType var5 = TypeUtil.getNonAlias(var4);
      if (var5 instanceof IStructureType) {
         this.ut = (IStructureType)var5;
      }

      this.data = new byte[var4.getSize()];
      this.bigend = var1.getStandardEndianess().isBig();
   }

   public boolean commit() {
      try {
         this.vm.write(this.addr, this.data.length, this.data, 0);
         return true;
      } catch (MemoryException var1) {
         return false;
      }
   }

   public boolean set(String var1, Object var2) {
      if (this.ut == null) {
         throw new IllegalStateException("The provided type was not a structured");
      } else {
         IStructureTypeField var3 = this.ut.getFieldByName(var1);
         if (var3 == null) {
            return false;
         } else {
            INativeType var4 = TypeUtil.getNonAlias(var3.getType());
            if (!TypeUtil.isPrimitive(var4)) {
               return false;
            } else {
               IPrimitiveType var5 = (IPrimitiveType)var4;
               int var6 = var3.getOffset();
               if (var5.getCategory() == PrimitiveCategory.FLOAT) {
                  double var7;
                  if (var2 instanceof Double) {
                     var7 = (Double)var2;
                  } else {
                     if (!(var2 instanceof Float)) {
                        return false;
                     }

                     var7 = ((Float)var2).floatValue();
                  }

                  switch (var4.getSize()) {
                     case 4:
                        this.setFloatAt(var6, (float)var7);
                        break;
                     case 8:
                        this.setDoubleAt(var6, var7);
                        break;
                     default:
                        return false;
                  }
               } else {
                  if (var5.getCategory() != PrimitiveCategory.INTEGER && var5.getCategory() != PrimitiveCategory.UNSIGNED) {
                     return false;
                  }

                  long var9;
                  if (var5.getCategory() == PrimitiveCategory.INTEGER) {
                     if (var2 instanceof Byte) {
                        var9 = ((Byte)var2).byteValue();
                     } else if (var2 instanceof Short) {
                        var9 = ((Short)var2).shortValue();
                     } else if (var2 instanceof Integer) {
                        var9 = ((Integer)var2).intValue();
                     } else {
                        if (!(var2 instanceof Long)) {
                           return false;
                        }

                        var9 = (Long)var2;
                     }
                  } else if (var2 instanceof Byte) {
                     var9 = ((Byte)var2).byteValue() & 255L;
                  } else if (var2 instanceof Short) {
                     var9 = ((Short)var2).shortValue() & 65535L;
                  } else if (var2 instanceof Integer) {
                     var9 = ((Integer)var2).intValue() & 4294967295L;
                  } else {
                     if (!(var2 instanceof Long)) {
                        return false;
                     }

                     var9 = (Long)var2;
                  }

                  switch (var4.getSize()) {
                     case 1:
                        this.setByteAt(var6, (byte)var9);
                        break;
                     case 2:
                        this.setShortAt(var6, (short)var9);
                        break;
                     case 3:
                     case 5:
                     case 6:
                     case 7:
                     default:
                        return false;
                     case 4:
                        this.setIntAt(var6, (int)var9);
                        break;
                     case 8:
                        this.setLongAt(var6, var9);
                  }
               }

               return true;
            }
         }
      }
   }

   public void setByteAt(int var1, byte var2) {
      this.data[var1] = var2;
   }

   public void setShortAt(int var1, short var2) {
      if (this.bigend) {
         EndianUtil.shortToBEBytes(var2, this.data, var1);
      } else {
         EndianUtil.shortToLEBytes(var2, this.data, var1);
      }
   }

   public void setIntAt(int var1, int var2) {
      if (this.bigend) {
         EndianUtil.intToBEBytes(var2, this.data, var1);
      } else {
         EndianUtil.intToLEBytes(var2, this.data, var1);
      }
   }

   public void setLongAt(int var1, long var2) {
      if (this.bigend) {
         EndianUtil.longToBEBytes(var2, this.data, var1);
      } else {
         EndianUtil.longToLEBytes(var2, this.data, var1);
      }
   }

   public void setFloatAt(int var1, float var2) {
      int var3 = Float.floatToIntBits(var2);
      this.setIntAt(var1, var3);
   }

   public void setDoubleAt(int var1, double var2) {
      long var4 = Double.doubleToLongBits(var2);
      this.setLongAt(var1, var4);
   }
}
