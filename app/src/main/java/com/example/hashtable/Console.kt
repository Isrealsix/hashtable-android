//import java.awt.BorderLayout
//import java.awt.Frame
//import java.awt.Toolkit
//import java.awt.event.ActionEvent
//import java.io.IOException
//import javax.swing.*
//
//class Console {
//    var jFrame: Frame = frame
//    var jPanel = JPanel()
//    var size = 0
//
//    /*String key;
//    String value;
//    Double valueX;
//    Double valueY;*/
//    var hash: Hash = Hash(0)
//    var factory = ObjectFactory().apply {
//        objectRecorder(StringObjectBuilder())
//        objectRecorder(IntObjectBuilder())
//    }
//    var vector2D = Vector2D()
//    var hashSerialization = HashSerialization()
//
//    init {
//        jFrame.add(jPanel)
//        val jlable = JLabel("Insert HashMap size: ")
//        jlable.setBounds(200, 50, 30, 40)
//        jPanel.add(jlable)
//        val jText = JTextField(10)
//        jPanel.add(jText)
//        jText.setBounds(200, 50, 100, 100)
//        jPanel.revalidate()
//        val border = BorderFactory.createEtchedBorder()
//        jPanel.border = border
//        val jSetButton = JButton("Add")
//        jPanel.add(jSetButton)
//        jSetButton.addActionListener {
//            try {
//                val getValue = jText.text
//                size = getValue.toInt()
//                hash = Hash(size)
//                if (size > 0) {
//                    val myDialog = MyDialog()
//                    myDialog.isVisible = true
//                    myDialog.setBounds(500, 500, 500, 450)
//                } else {
//                    println("Incorrect value")
//                }
//            } catch (exception: Exception) {
//                println("Text field should contain integer value")
//            }
//        }
//        jPanel.revalidate()
//    }
//
//    internal inner class MyDialog : JDialog(jFrame, "Type choosing", true) {
//        init {
//            val jStringButton = JButton(MyActionString())
//            jStringButton.text = "String"
//            add(jStringButton, BorderLayout.NORTH)
//            val jVectorButton = JButton(MyActionVector())
//            jVectorButton.text = "Vector"
//            add(jVectorButton, BorderLayout.SOUTH)
//            setBounds(550, 400, 250, 100)
//        }
//    }
//
//    internal inner class MyStringDialog : JDialog(jFrame, "String Console", true) {
//        init {
//            val jNumberLable = JLabel("Number: ")
//            add(jNumberLable, BorderLayout.BEFORE_FIRST_LINE)
//            val jNumberTextField = JTextField(20)
//            jNumberTextField.setBounds(50, 3, 250, 20)
//            add(jNumberTextField, BorderLayout.NORTH)
//            val jGenerate = JButton()
//            jGenerate.text = "Generate"
//            jGenerate.setBounds(210, 30, 100, 25)
//            add(jGenerate, BorderLayout.NORTH)
//            jGenerate.addActionListener {
//                val start = System.currentTimeMillis()
//                val numberValue = jNumberTextField.text
//                val number = numberValue.toInt()
//                val stringObjectBuilder = factory.getBuilderByName("String")
//                    ?: throw java.lang.IllegalStateException("Type not found")
//                for (i in 0 until number) {
//                    hash.put(stringObjectBuilder.create())
//                }
//                // увеличение хэш-таблицы
//                //hash = hash.resizeHash(hash)
//                val finish = System.currentTimeMillis() - start
//                println("Время: $finish")
//            }
//            val jIDGetLable = JLabel("ID: ")
//            jIDGetLable.setBounds(1, 75, 20, 15)
//            add(jIDGetLable, BorderLayout.NORTH)
//            val jIDGetTextField = JTextField(20)
//            jIDGetTextField.setBounds(18, 75, 300, 20)
//            add(jIDGetTextField, BorderLayout.NORTH)
//            val jGetHash = JButton()
//            jGetHash.text = "Hash"
//            jGetHash.setBounds(73, 100, 70, 25)
//            add(jGetHash, BorderLayout.NORTH)
//            jGetHash.addActionListener { println(hash.toString()) }
//            val jGetID = JButton()
//            jGetID.text = "GetID"
//            jGetID.setBounds(150, 100, 70, 25)
//            add(jGetID, BorderLayout.NORTH)
//            jGetID.addActionListener {
//                val keyGetter = jIDGetTextField.text
//                val number = keyGetter.toInt()
//                println(hash!![number])
//            }
//            val jRemove = JButton()
//            jRemove.text = "Remove"
//            jRemove.setBounds(227, 100, 90, 25)
//            add(jRemove, BorderLayout.NORTH)
//            jRemove.addActionListener {
//                val keyRemover = jIDGetTextField.text
//                val number = keyRemover.toInt()
//                println(hash!!.remove(number))
//            }
//            val jSerialize = JButton()
//            jSerialize.text = "Serialize"
//            jSerialize.setBounds(1, 130, 317, 25)
//            add(jSerialize, BorderLayout.NORTH)
//            jSerialize.addActionListener {
//                try {
//                    hashSerialization.saveToFile("hashMap.xml", hash!!, StringObjectBuilder())
//                } catch (ex: IOException) {
//                    throw RuntimeException(ex)
//                }
//            }
//            val jDeserialize = JButton()
//            jDeserialize.text = "Deserialize"
//            jDeserialize.setBounds(1, 160, 317, 25)
//            add(jDeserialize, BorderLayout.NORTH)
//
//            val jRes = JButton()
//            jRes.text = "Resize"
//            jRes.setBounds(1, 180, 317, 25)
//            add(jRes, BorderLayout.NORTH)
//            jRes.addActionListener { hash = hash.resizeHash() }
//
//            jDeserialize.addActionListener {
//                val hashSerialization = HashSerialization()
//                try {
//                    hashSerialization.loadFromFile("hashMap.xml", hash!!)
//                } catch (ex: IOException) {
//                    throw RuntimeException(ex)
//                } catch (ex: ClassNotFoundException) {
//                    throw RuntimeException(ex)
//                }
//            }
//            setBounds(400, 400, 335, 270)
//        }
//    }
//
//    internal inner class MyVectorDialog : JDialog(jFrame, "Vector Console", true) {
//        init {
//            val jNumberLable = JLabel("Number: ")
//            add(jNumberLable, BorderLayout.BEFORE_FIRST_LINE)
//            val jNumberTextField = JTextField(20)
//            jNumberTextField.setBounds(50, 3, 250, 20)
//            add(jNumberTextField, BorderLayout.NORTH)
//            val jGenerate = JButton()
//            jGenerate.text = "Generate"
//            jGenerate.setBounds(210, 30, 100, 25)
//            add(jGenerate, BorderLayout.NORTH)
//            jGenerate.addActionListener {
//                val numberValue = jNumberTextField.text
//                val number = numberValue.toInt()
//                for (i in 0 until number) {
//                    hash.put(vector2D.create())
//                    //factory = ObjectFactory(vector2D)
//                }
//                factory.getBuilderByName("Vector2D")
//                hash = hash.resizeHash()
//            }
//            val jIDGetLable = JLabel("ID: ")
//            jIDGetLable.setBounds(1, 75, 20, 15)
//            add(jIDGetLable, BorderLayout.NORTH)
//            val jIDGetTextField = JTextField(20)
//            jIDGetTextField.setBounds(18, 75, 300, 20)
//            add(jIDGetTextField, BorderLayout.NORTH)
//            val jGetHash = JButton()
//            jGetHash.text = "Hash"
//            jGetHash.setBounds(73, 100, 70, 25)
//            add(jGetHash, BorderLayout.NORTH)
//            jGetHash.addActionListener { println(hash.toString()) }
//            val jGetID = JButton()
//            jGetID.text = "GetID"
//            jGetID.setBounds(150, 100, 70, 25)
//            add(jGetID, BorderLayout.NORTH)
//            jGetID.addActionListener {
//                val keyGetter = jIDGetTextField.text
//                val number = keyGetter.toInt()
//                println(hash!![number])
//            }
//            val jRemove = JButton()
//            jRemove.text = "Remove"
//            jRemove.setBounds(227, 100, 90, 25)
//            add(jRemove, BorderLayout.NORTH)
//            jRemove.addActionListener {
//                val keyRemover = jIDGetTextField.text
//                val number = keyRemover.toInt()
//                println(hash!!.remove(number))
//            }
//            val jSerialize = JButton()
//            jSerialize.text = "Serialize"
//            jSerialize.setBounds(1, 130, 317, 25)
//            add(jSerialize, BorderLayout.NORTH)
//            jSerialize.addActionListener {
//                try {
//                    hashSerialization.saveToFile("hashMap.xml", hash!!, vector2D)
//                } catch (ex: IOException) {
//                    throw RuntimeException(ex)
//                }
//            }
//            val jDeserialize = JButton()
//            jDeserialize.text = "Deserialize"
//            jDeserialize.setBounds(1, 160, 317, 25)
//            add(jDeserialize, BorderLayout.NORTH)
//            jDeserialize.addActionListener {
//                val hashSerialization = HashSerialization()
//                try {
//                    hashSerialization.loadFromFile("hashMap.xml", hash!!)
//                } catch (ex: IOException) {
//                    throw RuntimeException(ex)
//                } catch (ex: ClassNotFoundException) {
//                    throw RuntimeException(ex)
//                } catch (ex: Exception) {
//                    throw RuntimeException(ex)
//                }
//            }
//            setBounds(400, 400, 335, 270)
//        }
//    }
//
//    internal inner class MyActionString : AbstractAction() {
//        override fun actionPerformed(e: ActionEvent) {
//            val myStringDialog = MyStringDialog()
//            myStringDialog.isVisible = true
//        }
//    }
//
//    internal inner class MyActionVector : AbstractAction() {
//        override fun actionPerformed(e: ActionEvent) {
//            val myVectorDialog = MyVectorDialog()
//            myVectorDialog.isVisible = true
//        }
//    }
//
//    val frame: JFrame
//        get() {
//            val jFrame = JFrame()
//            jFrame.isVisible = true
//            jFrame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
//            val tk = Toolkit.getDefaultToolkit()
//            val d = tk.screenSize
//            jFrame.setBounds(d.width / 3, d.height / 3, 300, 150)
//            jFrame.title = "L1 Console"
//            return jFrame
//        }
//}
