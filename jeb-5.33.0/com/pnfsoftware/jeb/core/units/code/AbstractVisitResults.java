package com.pnfsoftware.jeb.core.units.code;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public abstract class AbstractVisitResults implements IVisitResults {
   private int flags;
   public boolean skipAssignmentDestination;
   private boolean visitedSuccessfully = true;
   private int value;
   private boolean interruptVisit;
   public boolean skipVisitingChildren;
   public Object currentNode;
   public int visitedChildPosition = -1;
   public Deque parents;

   public AbstractVisitResults() {
      this(0);
   }

   public AbstractVisitResults(int var1) {
      if ((var1 & 1) != 0) {
         this.parents = new ArrayDeque();
      }

      if ((var1 & 2) != 0) {
         this.skipAssignmentDestination = true;
      }

      this.flags = var1;
   }

   public int getFlags() {
      return this.flags;
   }

   public void pushParent(Object var1) {
      if (this.parents != null) {
         this.parents.push(var1);
      }
   }

   public void popParent() {
      if (this.parents != null) {
         this.parents.pop();
      }
   }

   @Override
   public Iterator parentsIterator() {
      return this.parents != null ? this.parents.iterator() : null;
   }

   @Override
   public Object parent(int var1) {
      if (this.parents == null) {
         return null;
      } else {
         if (var1 < 0) {
            var1 += this.parents.size();
            if (var1 < 0) {
               return null;
            }
         }

         if (var1 >= this.parents.size()) {
            return null;
         } else {
            Iterator var2 = this.parents.iterator();

            while (var1-- > 0) {
               var2.next();
            }

            return var2.next();
         }
      }
   }

   @Override
   public void interrupt(boolean var1) {
      this.interruptVisit = true;
      this.visitedSuccessfully = var1;
   }

   @Override
   public void interrupt(boolean var1, int var2) {
      this.interruptVisit = true;
      this.visitedSuccessfully = var1;
      this.value = var2;
   }

   @Override
   public boolean isInterruptedVisit() {
      return this.interruptVisit;
   }

   @Override
   public void setVisitResult(boolean var1) {
      this.visitedSuccessfully = var1;
   }

   @Override
   public boolean isVisitedSuccessfully() {
      return this.visitedSuccessfully;
   }

   @Override
   public int getValue() {
      return this.value;
   }

   @Override
   public void setValue(int var1) {
      this.value = var1;
   }

   @Override
   public void skipChildren() {
      this.skipVisitingChildren = true;
   }

   @Override
   public void setReplacedNode(Object var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.currentNode = var1;
      }
   }
}
