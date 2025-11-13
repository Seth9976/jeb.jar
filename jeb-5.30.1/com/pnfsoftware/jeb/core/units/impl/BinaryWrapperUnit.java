package com.pnfsoftware.jeb.core.units.impl;

import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.units.IBinaryUnit;
import com.pnfsoftware.jeb.core.units.IUnitProvider;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class BinaryWrapperUnit extends WrapperUnit implements IBinaryUnit {
   public BinaryWrapperUnit(IBinaryUnit var1, IUnitProvider var2) {
      super(var1, var2);
   }

   @Override
   public IInput getInput() {
      return ((IBinaryUnit)this.getWrap()).getInput();
   }

   @Override
   public String getMimeType() {
      return ((IBinaryUnit)this.getWrap()).getMimeType();
   }
}
