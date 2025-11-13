package com.pnfsoftware.jeb.core.units.code.asm.type;

public interface IVirtualTableDefinition {
   int size();

   IStructureType getVirtualTableType();

   IClassType getClassType();
}
