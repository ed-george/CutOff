package co.uk.edgeorgedev.cutoff;

/*
 * Copyright (C) 2014 Ed George
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal 
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *  
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *  
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

@SupportedAnnotationTypes({"co.uk.edgeorgedev.cutoff.Deadline"})
public class AnnotationProcessor extends AbstractProcessor
{
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env)
	{
		Messager messager = processingEnv.getMessager();

		for (TypeElement te : annotations)
		{
			for (Element e : env.getElementsAnnotatedWith(te))
			{
				Deadline dl = e.getAnnotation(Deadline.class);

				DateFormat df = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
				
				try {
					Date date = df.parse(dl.value());
					Date now = new Date();
					
					if(now.after(date)){
						messager.printMessage(Diagnostic.Kind.ERROR, "CUTOFF: This method has reached its deadline: " + e.toString(), e);
					}else{
						messager.printMessage(Diagnostic.Kind.WARNING, "CUTOFF: There is a deadline scheduled for " + dl.value() + " method " + e.toString(), e);
					}
					
				} catch (ParseException e1) {
					messager.printMessage(Diagnostic.Kind.ERROR, "CUTOFF: CutOff only supports dates in \"yyyy-mm-dd\" format. Could not parse " + dl.value() +" as a date.", e);
				}
			
			}
		}
		return true;
	}

	@Override
	public SourceVersion getSupportedSourceVersion()
	{
		return SourceVersion.latestSupported();
	}
}
