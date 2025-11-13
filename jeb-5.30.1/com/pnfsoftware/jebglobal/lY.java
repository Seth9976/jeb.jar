package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.codeobject.IELFSymbolProcessor;
import com.pnfsoftware.jeb.core.units.codeobject.IELFSymbolProcessorFactory;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;

public class lY implements IELFSymbolProcessorFactory {
   @Override
   public boolean canProcess(IELFUnit var1) {
      int var2 = var1.getHeader().getMachine();
      return var2 == 40 || var2 == 183;
   }

   @Override
   public IELFSymbolProcessor createInstance(IELFUnit var1) {
      long var2 = var1.getLoaderInformation().getImageBase();
      long var4 = var2 + var1.getLoaderInformation().getImageSize();
      return new Z(var2, var4, var1.isELF64());
   }
}
