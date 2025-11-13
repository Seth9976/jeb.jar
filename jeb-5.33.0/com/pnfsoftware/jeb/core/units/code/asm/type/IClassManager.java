package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeClassItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import java.util.List;

public interface IClassManager {
   INativeClassItem createClass(IClassType var1);

   void setVirtualTableMethods(INativeClassItem var1, List var2);

   void addNonVirtualMethod(INativeClassItem var1, INativeMethodItem var2);

   void addStaticMethod(INativeClassItem var1, INativeMethodItem var2);

   void completeClassInitialization(INativeClassItem var1);
}
