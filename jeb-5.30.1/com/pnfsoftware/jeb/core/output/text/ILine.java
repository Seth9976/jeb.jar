package com.pnfsoftware.jeb.core.output.text;

import com.pnfsoftware.jeb.core.output.text.impl.Line;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface ILine {
   ILine EMPTY_LINE = new Line("");
   int FLAG_DISABLED_LINE = 1;

   CharSequence getText();

   List getItems();

   List getMarks();

   default int getFlags() {
      return 0;
   }
}
