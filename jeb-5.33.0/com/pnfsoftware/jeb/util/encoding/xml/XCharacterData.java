package com.pnfsoftware.jeb.util.encoding.xml;

import com.pnfsoftware.jeb.util.base.Assert;
import org.w3c.dom.CharacterData;
import org.w3c.dom.DOMException;

public abstract class XCharacterData extends XNode implements CharacterData {
   final String value;

   public XCharacterData(String var1) {
      Assert.a(var1 != null);
      this.value = var1;
   }

   @Override
   public String getNodeValue() throws DOMException {
      return this.value;
   }

   @Override
   public String getData() throws DOMException {
      return this.value;
   }

   @Override
   public void setData(String var1) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public int getLength() {
      return this.value.length();
   }

   @Override
   public String substringData(int var1, int var2) throws DOMException {
      return this.value.substring(var1, var1 + var2);
   }

   @Override
   public void appendData(String var1) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public void insertData(int var1, String var2) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public void deleteData(int var1, int var2) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public void replaceData(int var1, int var2, String var3) throws DOMException {
      throw new UnsupportedOperationException();
   }

   @Override
   public String toString() {
      return this.value;
   }
}
