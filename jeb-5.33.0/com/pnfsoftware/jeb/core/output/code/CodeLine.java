package com.pnfsoftware.jeb.core.output.code;

import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.output.text.ILine;
import com.pnfsoftware.jeb.core.output.text.impl.TextMark;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@SerDisabled
public class CodeLine implements ILine {
   private StringBuilder text = new StringBuilder();
   private List items = Collections.emptyList();
   private List marks = Collections.emptyList();
   private ICodeCoordinates lineCoordinates = null;
   private TreeMap coordinates = new TreeMap();
   private int flags;

   @Override
   public CharSequence getText() {
      return this.text;
   }

   @Override
   public int getFlags() {
      return this.flags;
   }

   public void setFlags(int var1) {
      this.flags = var1;
   }

   public void addFlags(int var1) {
      this.flags |= var1;
   }

   public void removeFlags(int var1) {
      this.flags &= ~var1;
   }

   @Override
   public List getItems() {
      return this.items;
   }

   @Override
   public List getMarks() {
      return this.marks;
   }

   public void setCoordinates(ICodeCoordinates var1) {
      this.coordinates.put(this.text.length(), var1);
   }

   public Map getCoordinates() {
      return this.coordinates;
   }

   public ICodeCoordinates getCoordinates(int var1) {
      int var2 = -1;

      for (int var4 : this.coordinates.keySet()) {
         if (var1 < var4) {
            break;
         }

         var2 = var4;
      }

      return var2 < 0 ? this.lineCoordinates : (ICodeCoordinates)this.coordinates.get(var2);
   }

   public void setLineCoordinates(ICodeCoordinates var1) {
      this.lineCoordinates = var1;
   }

   public ICodeCoordinates getLineCoordinates() {
      return this.lineCoordinates;
   }

   int length() {
      return this.text.length();
   }

   boolean isEmpty() {
      return this.text.length() == 0;
   }

   void append(CharSequence var1) {
      this.text.append(var1);
   }

   boolean addItem(AssemblyItem var1) {
      if (this.items == Collections.EMPTY_LIST) {
         this.items = new ArrayList();
      }

      this.items.add(var1);
      var1.setLine(this);
      return true;
   }

   boolean addMark(TextMark var1) {
      if (this.marks == Collections.EMPTY_LIST) {
         this.marks = new ArrayList();
      }

      return this.marks.add(var1);
   }

   @Override
   public String toString() {
      return this.getText().toString();
   }
}
