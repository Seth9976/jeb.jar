package com.pnfsoftware.jeb.core.units.codeobject;

public interface IELFSymbolProcessorFactory {
   boolean canProcess(IELFUnit var1);

   IELFSymbolProcessor createInstance(IELFUnit var1);
}
