package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;
import java.util.List;

@Ser
public interface IEReturn extends IEStatement {
   IEGeneric getValue();

   void setValue(IEGeneric var1);

   List getValues();

   void setValues(Collection var1);

   @Override
   int getBitsize();
}
