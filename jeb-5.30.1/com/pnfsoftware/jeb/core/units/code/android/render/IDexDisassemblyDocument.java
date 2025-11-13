package com.pnfsoftware.jeb.core.units.code.android.render;

import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.output.text.ITextDocument;
import com.pnfsoftware.jeb.core.output.text.ITextDocumentPart;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;

public interface IDexDisassemblyDocument extends ITextDocument {
   DexDisassemblyProperties getPropertyOverrides();

   void setPropertyOverrides(DexDisassemblyProperties var1);

   ITextDocumentPart getItemDisassembly(ICodeCoordinates var1);

   IDexUnit getUnit();

   IDexItemToAnchor getCodeItemToAnchor();
}
