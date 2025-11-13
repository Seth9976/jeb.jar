package com.pnfsoftware.jeb.core.output;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface IUnitFormatter {
   int getPresentationCount();

   IUnitDocumentPresentation getPresentation(int var1);

   void addPresentation(IUnitDocumentPresentation var1, boolean var2);

   void insertPresentation(int var1, IUnitDocumentPresentation var2, boolean var3);

   void removePresentation(int var1);

   boolean isPersisted(int var1);

   List getPresentations();

   void discardTransientPresentations();
}
