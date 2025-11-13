package com.pnfsoftware.jeb.core.units.code;

import com.pnfsoftware.jeb.core.output.text.ITextDocument;
import com.pnfsoftware.jeb.core.units.IInteractiveUnit;

public interface ISourceUnit extends IInteractiveUnit {
   IDecompilerUnit getDecompiler();

   String getFileExtension();

   String getFullyQualifiedName();

   ITextDocument getSourceDocument();

   String getSource();
}
