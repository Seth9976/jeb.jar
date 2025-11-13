package com.pnfsoftware.jeb.core.output.text.impl;

import com.pnfsoftware.jeb.core.output.text.ILine;
import com.pnfsoftware.jeb.util.format.CharSequences;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Ser
public class Line implements ILine {
   @SerId(1)
   private CharSequence text;
   @SerId(2)
   private List items;
   @SerId(3)
   private List marks;

   public Line(CharSequence var1) {
      this(var1, null, null);
   }

   public Line(CharSequence var1, List var2) {
      this(var1, var2, null);
   }

   public Line(CharSequence var1, List var2, List var3) {
      this.setText(var1);
      if (var2 == null) {
         var2 = Collections.emptyList();
      }

      this.items = var2;

      for (TextItem var5 : var2) {
         var5.setLine(this);
      }

      if (var3 == null) {
         var3 = Collections.emptyList();
      }

      this.marks = var3;
   }

   @Override
   public CharSequence getText() {
      return this.text;
   }

   public void setText(CharSequence var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Line cannot be null");
      } else if (CharSequences.indexOf2(var1, '\r', '\n') >= 0) {
         throw new IllegalArgumentException("Line cannot contain new-line characters");
      } else {
         this.text = var1;
      }
   }

   @Override
   public List getItems() {
      return this.items;
   }

   public void addItem(TextItem var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Null item");
      } else {
         if (this.items == Collections.EMPTY_LIST) {
            this.items = new ArrayList();
         }

         for (int var2 = 0; var2 < this.items.size(); var2++) {
            if (var1.getOffset() < ((TextItem)this.items.get(var2)).getOffset()) {
               this.items.add(var2, var1);
               return;
            }
         }

         this.items.add(var1);
         var1.setLine(this);
      }
   }

   @Override
   public List getMarks() {
      return this.marks;
   }

   public void addMark(TextMark var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Null mark");
      } else {
         if (this.marks == Collections.EMPTY_LIST) {
            this.marks = new ArrayList();
         }

         this.marks.add(var1);
      }
   }

   @Override
   public String toString() {
      return Strings.ff("text=%s|items=%s|marks=%s", this.getText(), Strings.joinList(this.getItems()), Strings.joinList(this.getMarks()));
   }
}
