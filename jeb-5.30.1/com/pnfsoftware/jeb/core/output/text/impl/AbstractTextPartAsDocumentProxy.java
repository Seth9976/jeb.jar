package com.pnfsoftware.jeb.core.output.text.impl;

import com.pnfsoftware.jeb.core.exceptions.NotImplementedException;
import com.pnfsoftware.jeb.core.output.AddressConversionPrecision;
import com.pnfsoftware.jeb.core.output.CoordinatesConversionPrecision;
import com.pnfsoftware.jeb.core.output.text.IAnchor;
import com.pnfsoftware.jeb.core.output.text.ICoordinates;
import com.pnfsoftware.jeb.core.output.text.ITextDocument;
import com.pnfsoftware.jeb.core.output.text.ITextDocumentPart;
import com.pnfsoftware.jeb.core.output.text.TextDocumentUtil;
import com.pnfsoftware.jeb.core.output.text.TextPartUtil;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jeb.util.events.IEventSource;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.List;

@SerDisabled
public abstract class AbstractTextPartAsDocumentProxy implements ITextDocument {
   private ITextDocument idoc;
   private ITextDocumentPart part;

   public AbstractTextPartAsDocumentProxy(ITextDocument var1) {
      this.idoc = var1;
   }

   public ITextDocument getFullDocument() {
      return this.idoc;
   }

   protected abstract ITextDocumentPart getPartAsDocument();

   @Override
   public void dispose() {
      this.idoc.dispose();
   }

   @Override
   public IUnit getUnit() {
      return this.idoc.getUnit();
   }

   @Override
   public IPropertyManager getPropertyManager() {
      return this.idoc.getPropertyManager();
   }

   @Override
   public long getInitialAnchor() {
      if (this.part == null) {
         this.part = this.getPartAsDocument();
      }

      return this.part.getAnchor(0).getIdentifier();
   }

   @Override
   public long getFirstAnchor() {
      return this.getInitialAnchor();
   }

   @Override
   public long getAnchorCount() {
      if (this.part == null) {
         this.part = this.getPartAsDocument();
      }

      List var1 = this.part.getAnchors();
      return ((IAnchor)var1.get(var1.size() - 1)).getIdentifier() - ((IAnchor)var1.get(0)).getIdentifier() + 1L;
   }

   @Override
   public ITextDocumentPart getDocumentPart(long var1, int var3) {
      this.part = this.getPartAsDocument();
      return this.part;
   }

   @Override
   public ITextDocumentPart getDocumentPart(long var1, int var3, int var4) {
      return this.getPartAsDocument();
   }

   @Override
   public ITextDocumentPart getDocumentPart2(long var1, long var3) throws NotImplementedException {
      throw new NotImplementedException();
   }

   @Override
   public String coordinatesToAddress(ICoordinates var1) {
      return this.idoc.coordinatesToAddress(var1, AddressConversionPrecision.DEFAULT);
   }

   @Override
   public String coordinatesToAddress(ICoordinates var1, AddressConversionPrecision var2) {
      return this.idoc.coordinatesToAddress(var1, var2);
   }

   @Override
   public ICoordinates addressToCoordinates(String var1) {
      return this.addressToCoordinates(var1, null);
   }

   @Override
   public ICoordinates addressToCoordinates(String var1, CoordinatesConversionPrecision var2) {
      ICoordinates var3 = this.idoc.addressToCoordinates(var1, var2);
      if (var3 != null) {
         long var4 = var3.getAnchorId();
         if (this.part == null) {
            this.part = this.getPartAsDocument();
         }

         long var6 = TextPartUtil.getFirstAnchorId(this.part);
         long var8 = TextPartUtil.getLastAnchorId(this.part);
         if (var4 < var6 || var4 > var8) {
            var3 = null;
         }
      }

      return var3;
   }

   @Override
   public String format() {
      return TextDocumentUtil.getText(this);
   }

   @Override
   public void setParentSource(IEventSource var1) {
      this.idoc.setParentSource(var1);
   }

   @Override
   public IEventSource getParentSource() {
      return this.idoc.getParentSource();
   }

   @Override
   public int countListeners() {
      return this.idoc.countListeners();
   }

   @Override
   public List getListeners() {
      return this.idoc.getListeners();
   }

   @Override
   public void addListener(IEventListener var1) {
      this.idoc.addListener(var1);
   }

   @Override
   public void insertListener(int var1, IEventListener var2) {
      this.idoc.insertListener(var1, var2);
   }

   @Override
   public void removeListener(IEventListener var1) {
      this.idoc.removeListener(var1);
   }

   @Override
   public void notifyListeners(IEvent var1) {
      this.idoc.notifyListeners(var1);
   }
}
