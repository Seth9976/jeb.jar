package com.pnfsoftware.jeb.core.units.code.android.render;

import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClass;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;

public interface IDexItemToAnchor {
   int size();

   Long fromClass(IDexClass var1);

   Long fromField(IDexField var1);

   Long fromMethod(IDexMethod var1);

   Long fromInstruction(IDexMethod var1, BasicBlock var2);

   Long from(ICodeCoordinates var1);

   ICodeCoordinates get(long var1);
}
