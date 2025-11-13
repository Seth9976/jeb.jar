package com.pnfsoftware.jeb.core.output.text;

public interface IObjectLocation {
   Object getObject();

   int getLineBegin();

   int getLineEnd();

   int getColumnBegin();

   int getColumnEnd();
}
