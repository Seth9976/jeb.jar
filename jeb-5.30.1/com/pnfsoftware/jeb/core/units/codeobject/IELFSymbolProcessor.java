package com.pnfsoftware.jeb.core.units.codeobject;

public interface IELFSymbolProcessor {
   ISymbolInformation processSymbol(IELFSymbolEntry var1);

   boolean canImmediatelyUseSymbol();

   Iterable getSymbols(Iterable var1);
}
