package com.pnfsoftware.jeb.core.units.code.asm.items;

import java.util.List;

public interface IMethodTable {
   Long getAddress();

   int size();

   INativeMethodItem get(int var1);

   List getAll();

   int find(INativeMethodItem var1);
}
