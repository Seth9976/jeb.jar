package com.pnfsoftware.jeb.core.output;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IUnitDocumentPresentation {
   int DOC_ID_DISASSEMBLY = 1;

   long getId();

   String getLabel();

   boolean isDefaultRepresentation();

   IGenericDocument getDocument();

   void dispose();
}
