package com.pnfsoftware.jeb.core.units;

import org.w3c.dom.Document;

public interface IXmlUnit extends IAddressableUnit {
   boolean hasXmlDeclaration();

   Document getDocument();

   @Override
   Object getItemObject(long var1);
}
