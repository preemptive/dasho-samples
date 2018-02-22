/* Copyright 2018 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.example.spring.app;

import com.example.spring.beans.OutputBean;
import com.example.spring.util.Output;
import com.example.spring.util.PrintListener;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * A simple application that displays Bean XML and the life cycle of Spring
 * beans.
 */
public class MySpringApp implements PrintListener {

    private JFrame frame;
    private JTextPane textPane;
    private JScrollPane textScrollPane;
    private boolean isObfuscated = false;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MySpringApp().start();
                } catch (Exception e) {
                    Output.printError(e);
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public MySpringApp() {
        determineIfObfuscated();
        initialize();
        Output.setListener(this);
    }

    /**
     * Determine if DashO has been run
     */
    private void determineIfObfuscated() {
        try {
            isObfuscated = getClass().getDeclaredMethod("unused") == null;
        } catch (SecurityException e) {
            isObfuscated = true;
        } catch (NoSuchMethodException e) {
            isObfuscated = true;
        }
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame("Spring Bean Sample"
                + (isObfuscated ? " (Obfuscated)" : ""));
        frame.setBounds(isObfuscated ? 650 : 25, 50, 600, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(1, 1, 0, 0));
        int gridX = 0;
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0, 0};
        gbl_panel.rowHeights = new int[]{0, 0, 0};
        panel.setLayout(gbl_panel);

        JLabel lblNewLabel = new JLabel(isObfuscated ?
                "Click the areas to see how DashO obfuscated the Spring Beans." : "Click the areas to see the original Spring Beans.");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblNewLabel.insets = new Insets(5, 5, 5, 5);
        gbc_lblNewLabel.gridwidth = 4;
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 0;
        panel.add(lblNewLabel, gbc_lblNewLabel);

        JButton btnInitDestroy = new JButton("Life Cycle");
        GridBagConstraints gbc_btnInitDestroy = new GridBagConstraints();
        gbc_btnInitDestroy.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnInitDestroy.weightx = 1.0;
        gbc_btnInitDestroy.insets = new Insets(5, 5, 5, 5);
        gbc_btnInitDestroy.gridx = gridX++;
        gbc_btnInitDestroy.gridy = 1;
        panel.add(btnInitDestroy, gbc_btnInitDestroy);
        btnInitDestroy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outputLifeCycle();
            }
        });

        JButton btnProperty = new JButton("Properties");
        GridBagConstraints gbc_btnProperty = new GridBagConstraints();
        gbc_btnProperty.insets = new Insets(5, 5, 5, 5);
        gbc_btnProperty.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnProperty.weightx = 1.0;
        gbc_btnProperty.gridx = gridX++;
        gbc_btnProperty.gridy = 1;
        panel.add(btnProperty, gbc_btnProperty);
        btnProperty.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outputProperties();
            }
        });

        JButton btnLR = new JButton("Lookup/Replaced");
        GridBagConstraints gbc_btnLR = new GridBagConstraints();
        gbc_btnLR.insets = new Insets(5, 5, 5, 5);
        gbc_btnLR.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnLR.weightx = 1.0;
        gbc_btnLR.gridx = gridX++;
        gbc_btnLR.gridy = 1;
        panel.add(btnLR, gbc_btnLR);
        btnLR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outputLookupReplace();
            }
        });

        JButton btnConst = new JButton("Constants");
        GridBagConstraints gbc_btnConst = new GridBagConstraints();
        gbc_btnConst.insets = new Insets(5, 5, 5, 5);
        gbc_btnConst.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnConst.weightx = 1.0;
        gbc_btnConst.gridx = gridX++;
        gbc_btnConst.gridy = 1;
        panel.add(btnConst, gbc_btnConst);
        btnConst.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outputConstants();
            }
        });

        textPane = new JTextPane();
        textPane.setEditable(false);
        textScrollPane = new JScrollPane(textPane);
        textScrollPane
                .setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        GridBagConstraints gbc_textPane = new GridBagConstraints();
        gbc_textPane.weighty = 5.0;
        gbc_textPane.gridwidth = gridX;
        gbc_textPane.insets = new Insets(0, 0, 0, 5);
        gbc_textPane.fill = GridBagConstraints.BOTH;
        gbc_textPane.gridx = 0;
        gbc_textPane.gridy = 2;
        panel.add(textScrollPane, gbc_textPane);
    }

    /**
     * Starts the app.
     */
    public void start() {
        frame.setVisible(true);
    }

    /**
     * A line was printed
     *
     * @param line The printed line
     */
    public void linePrinted(String line) {
        String text = textPane.getText();
        textPane.setText(text + line);
    }

    /**
     * Shows the bean life cycle
     */
    private void outputLifeCycle() {
        try {
            setupNewTest(
                    "BeansWithInitDestroyFactoryMethods.xml",
                    "You should see the init-method, destroy-method, default-init-method, or default-destroy-method (and sometimes private methods) are called on the appropriate beans.",
                    "The correct methods are still called, but you can see they have been renamed.");
            long start = System.currentTimeMillis();
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                    "BeansWithInitDestroyFactoryMethods.xml");
            Output.printTime("Load Time", start);
            Output.step("Printing Beans: init-methods, default-init-methods, and factory-methods are called."
                    + (isObfuscated ? "Notice the renaming." : ""));
            MySpringApp.printBean(context, "MyDefBean");
            MySpringApp.printBean(context, "MyOwnBean");
            MySpringApp.printBean(context, "MyFactoryBean1");
            MySpringApp.printBean(context, "MyFactoryBean2");
            Output.step("Printing Child Beans: init-methods and factory-methods are called."
                    + (isObfuscated ? "Notice the renaming." : ""));
            MySpringApp.printBean(context, "ChildOfAbstract");
            MySpringApp.printBean(context, "ChildOfChild");
            context.registerShutdownHook();
            Output.step("Destroying Context: destroy-methods and default-destroy-methods are called");
            context.close();
        } catch (Throwable e) {
            Output.printError(e);
        } finally {
            finishNewTest();
        }
    }

    /**
     * Shows the properties from Beans
     */
    private void outputProperties() {
        try {
            setupNewTest(
                    "BeansWithProperties.xml",
                    "If you look at the XML, you will notice classnames have been used as either values or types of arguments.  Also pay attention to the property names and methods.",
                    "You should see the classnames passed as parameters or constructor arguments have been renamed.  The bean property names and methods have also been renamed.");
            long start = System.currentTimeMillis();
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                    "BeansWithProperties.xml");
            Output.printTime("Load Time", start);
            Output.step("Printing Beans: "
                    + (isObfuscated ? "Notice the same greetings are printed."
                    : ""));
            MySpringApp.printBean(context, "classBean1");
            MySpringApp.printBean(context, "classBean2");
            MySpringApp.printBean(context, "listBean");
            MySpringApp.printBean(context, "propBean");
            Output.step("Printing Child Beans: ");
            MySpringApp.printBean(context, "ChildOfAbstract");
            MySpringApp.printBean(context, "ChildOfChild");
            context.registerShutdownHook();
            context.close();
        } catch (Throwable e) {
            Output.printError(e);
        } finally {
            finishNewTest();
        }
    }

    /**
     * Shows the lookup replace functionality
     */
    private void outputLookupReplace() {
        try {
            setupNewTest(
                    "BeansWithLookupReplacedMethods.xml",
                    "You should see the use of lookup-method and replaced-method elements.",
                    "The lookup-method and replaced-method names have been renamed.");
            long start = System.currentTimeMillis();
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                    "BeansWithLookupReplacedMethods.xml");
            Output.printTime("Load Time", start);
            Output.step("Printing Bean:");
            MySpringApp.printBean(context, "myLRBean");
            Output.step("Printing Child Beans:");
            MySpringApp.printBean(context, "ChildOfAbstract");
            MySpringApp.printBean(context, "ChildOfChild");
            context.registerShutdownHook();
            context.close();
        } catch (Throwable e) {
            Output.printError(e);
        } finally {
            finishNewTest();
        }
    }

    /**
     * Shows the constants from Beans
     */
    private void outputConstants() {
        try {
            setupNewTest(
                    "BeansWithConstantValues.xml",
                    "If you look at the XML, you will notice constant values have been used as either values or ids.",
                    "You should see the contants passed as values or ids have been renamed.");
            long start = System.currentTimeMillis();
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                    "BeansWithConstantValues.xml");
            Output.printTime("Load Time", start);
            Output.step("Printing Beans: "
                    + (isObfuscated ? "Notice the same questions and answers are printed."
                    : "Look at the questions and answers."));
            MySpringApp.printBean(context, "question_answer_1");
            MySpringApp.printBean(context, "question_answer_2");
            MySpringApp.printBean(context, "question_answer_3");
            MySpringApp.printBean(context, "question_answer_4");
            context.registerShutdownHook();
            context.close();
        } catch (Throwable e) {
            Output.printError(e);
        } finally {
            finishNewTest();
        }
    }

    /**
     * Initializes a new test
     *
     * @param xmlFileName The XML file
     * @param unObText    The text to show when not obfuscated
     * @param obText      The text to show when obfuscated
     */
    private void setupNewTest(String xmlFileName, String unObText, String obText) {
        frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        textPane.setText("");
        Output.reset();
        Output.println(isObfuscated ? obText : unObText);
        Output.println();
        Output.printXMLFile(xmlFileName);
        Output.println();
    }

    /**
     * Finishes the test.
     */
    private void finishNewTest() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                textScrollPane.getViewport().setViewPosition(new Point(0, 0));
            }
        });
        frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    /**
     * Prints a bean
     *
     * @param context The context
     * @param bean    The bean
     */
    private static void printBean(ClassPathXmlApplicationContext context, String bean_id) {
        Output.printStars("Bean ID: " + bean_id);
        OutputBean bean = (OutputBean)context.getBean(bean_id);
        bean.output();
        Output.printStarLine();
    }


    @SuppressWarnings("unused")
    private void unused() {
        // Used to determine if the application has been obfuscated.
    }
}
