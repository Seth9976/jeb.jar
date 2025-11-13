package com.pnfsoftware.jeb.client.api;

import com.pnfsoftware.jeb.core.output.IGenericDocument;
import com.pnfsoftware.jeb.core.output.text.ICoordinates;
import java.util.List;

public interface IUnitTextFragment extends IUnitFragment {
   IGenericDocument getDocument();

   String getSelectedText();

   ICoordinates getCaretCoordinates();

   void setCaretCoordinates(ICoordinates var1, boolean var2);

   List getDocumentObjectsAtCaret();
}
