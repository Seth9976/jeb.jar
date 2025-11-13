package com.pnfsoftware.jeb.core.output.text;

import com.pnfsoftware.jeb.core.exceptions.NotImplementedException;
import com.pnfsoftware.jeb.core.output.AddressConversionPrecision;
import com.pnfsoftware.jeb.core.output.CoordinatesConversionPrecision;
import com.pnfsoftware.jeb.core.output.IGenericDocument;
import com.pnfsoftware.jeb.util.events.IEventSource;

public interface ITextDocument extends IGenericDocument, IEventSource {
   long getInitialAnchor();

   long getFirstAnchor();

   long getAnchorCount();

   ITextDocumentPart getDocumentPart(long var1, int var3, int var4);

   ITextDocumentPart getDocumentPart(long var1, int var3);

   ITextDocumentPart getDocumentPart2(long var1, long var3) throws NotImplementedException;

   String coordinatesToAddress(ICoordinates var1);

   String coordinatesToAddress(ICoordinates var1, AddressConversionPrecision var2);

   ICoordinates addressToCoordinates(String var1);

   ICoordinates addressToCoordinates(String var1, CoordinatesConversionPrecision var2);

   String format();

   default boolean hasBinaryRepresentation() {
      return this.getBinaryRepresentation() != null;
   }

   default IBinaryRepresentation getBinaryRepresentation() {
      return null;
   }
}
