package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItemListenable;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItemListener;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;
import java.util.List;

@Ser
public interface IWildcardTypeManager extends INativeItemListenable {
   @Override
   void addListener(INativeItemListener var1);

   @Override
   void removeListener(INativeItemListener var1);

   ITypeManager getNativeTypeManager();

   int getSlotBitsize();

   int getPointerBitsize();

   IWildcardType create(String var1);

   IWildcardType create(INativeType var1);

   IWildcardType createWithMaximumBitsize(int var1);

   IWildcardType createWithSlotcount(int var1);

   IWildcardType createWithBitsizes(int var1, int var2);

   IWildcardType createWithEffectiveBitsize(int var1);

   IWildcardType createPointer(int var1);

   IWildcardPrototype createPrototype(ICallingConvention var1, IWildcardType var2, List var3, Collection var4);

   IWildcardPrototype createPrototype(ICallingConvention var1, List var2, List var3, Collection var4);

   IWildcardPrototype createPrototype(IPrototypeItem var1);

   default IWildcardType parse(String var1) {
      return this.parse(var1, 0);
   }

   IWildcardType parse(String var1, int var2);
}
