package com.pnfsoftware.jeb.core.units.codeobject;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class SymbolInformation implements ISymbolInformation {
   @SerId(1)
   SymbolType type;
   @SerId(2)
   int flags;
   @SerId(3)
   long identifier;
   @SerId(4)
   String name;
   @SerId(5)
   long address;
   @SerId(6)
   long symbolAddress;
   @SerId(7)
   long symbolSize;
   @SerId(8)
   int procMode;
   @SerId(9)
   String dataTypeInfo;

   public SymbolInformation(SymbolType var1, int var2, long var3, String var5, long var6, long var8, long var10) {
      this.type = var1;
      this.flags = var2;
      this.identifier = var3;
      this.name = var5;
      this.address = var6;
      this.symbolAddress = var8;
      this.symbolSize = var10;
   }

   @Override
   public SymbolType getType() {
      return this.type;
   }

   public void setType(SymbolType var1) {
      this.type = var1;
   }

   @Override
   public int getFlags() {
      return this.flags;
   }

   public void setFlags(int var1) {
      this.flags = var1;
   }

   @Override
   public long getIdentifier() {
      return this.identifier;
   }

   public void setIdentifier(long var1) {
      this.identifier = var1;
   }

   @Override
   public String getName() {
      return this.name;
   }

   public void setName(String var1) {
      this.name = var1;
   }

   @Override
   public long getRelativeAddress() {
      return this.address;
   }

   public void setRelativeAddress(long var1) {
      this.address = var1;
   }

   @Override
   public long getSymbolRelativeAddress() {
      return this.symbolAddress;
   }

   public void setSymbolRelativeAddress(long var1) {
      this.symbolAddress = var1;
   }

   @Override
   public long getSymbolSize() {
      return this.symbolSize;
   }

   public void setSymbolSize(long var1) {
      this.symbolSize = var1;
   }

   @Override
   public String getSymbolDataTypeInformation() {
      return this.dataTypeInfo;
   }

   public void setSymbolDataTypeInformation(String var1) {
      this.dataTypeInfo = var1;
   }

   @Override
   public int getProcessorMode() {
      return this.procMode;
   }

   public void setProcessorMode(int var1) {
      this.procMode = var1;
   }

   public boolean isInternal() {
      return (this.flags & 2) == 0 && (this.flags & 1) == 0;
   }

   @Override
   public String toString() {
      return Strings.ff(
         "%s|%X|id=%X|\"%s\"|a=%X|symb@%X,s=%X|dti=%s|pm=%d",
         this.getType(),
         this.getFlags(),
         this.getIdentifier(),
         this.getName(),
         this.getRelativeAddress(),
         this.getSymbolRelativeAddress(),
         this.getSymbolSize(),
         this.getSymbolDataTypeInformation(),
         this.getProcessorMode()
      );
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (int)(this.address ^ this.address >>> 32);
      var1 = 31 * var1 + this.flags;
      var1 = 31 * var1 + (int)(this.identifier ^ this.identifier >>> 32);
      var1 = 31 * var1 + (this.name == null ? 0 : this.name.hashCode());
      var1 = 31 * var1 + (int)(this.symbolAddress ^ this.symbolAddress >>> 32);
      var1 = 31 * var1 + (int)(this.symbolSize ^ this.symbolSize >>> 32);
      var1 = 31 * var1 + (this.type == null ? 0 : this.type.hashCode());
      var1 = 31 * var1 + (this.dataTypeInfo == null ? 0 : this.dataTypeInfo.hashCode());
      return 31 * var1 + this.procMode;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         SymbolInformation var2 = (SymbolInformation)var1;
         if (this.address != var2.address) {
            return false;
         } else if (this.flags != var2.flags) {
            return false;
         } else if (this.identifier != var2.identifier) {
            return false;
         } else {
            if (this.name == null) {
               if (var2.name != null) {
                  return false;
               }
            } else if (!this.name.equals(var2.name)) {
               return false;
            }

            if (this.symbolAddress != var2.symbolAddress) {
               return false;
            } else if (this.symbolSize != var2.symbolSize) {
               return false;
            } else if (this.type != var2.type) {
               return false;
            } else {
               if (this.dataTypeInfo == null) {
                  if (var2.dataTypeInfo != null) {
                     return false;
                  }
               } else if (!this.dataTypeInfo.equals(var2.dataTypeInfo)) {
                  return false;
               }

               return this.procMode == var2.procMode;
            }
         }
      }
   }
}
