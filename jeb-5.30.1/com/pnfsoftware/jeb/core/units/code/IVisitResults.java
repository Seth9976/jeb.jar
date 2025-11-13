package com.pnfsoftware.jeb.core.units.code;

import java.util.Iterator;

public interface IVisitResults {
   int FLAG_RECORD_PARENTS = 1;
   int FLAG_SKIP_ASSIGN_DST = 2;

   void interrupt(boolean var1);

   void interrupt(boolean var1, int var2);

   void setVisitResult(boolean var1);

   boolean isInterruptedVisit();

   boolean isVisitedSuccessfully();

   int getValue();

   void setValue(int var1);

   void skipChildren();

   void setReplacedNode(Object var1);

   Iterator parentsIterator();

   Object parent(int var1);
}
