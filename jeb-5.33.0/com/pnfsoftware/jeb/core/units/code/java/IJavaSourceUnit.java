package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.units.code.ISourceUnit;
import com.pnfsoftware.jeb.core.units.code.android.IDexDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaSourceUnit extends ISourceUnit {
   IDexDecompilerUnit getDecompiler();

   IDexItem getDexItem();

   IJavaElement getASTElement();

   void recordIdentifierPositions(long var1, ICodeCoordinates var3);
}
