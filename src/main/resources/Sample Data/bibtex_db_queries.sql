INSERT INTO bibtex_db (cite_key, cite_type, title, author, publisher, note, publication_year)
	VALUES ('Goodfellow-et-al-2016', 'book', 'Deep Learning', 'Ian Goodfellow and Yoshua Bengio and Aaron Courville',
			'MIT Press', '\url{http://www.deeplearningbook.org}, S.29, 51, 78, 117, 124, 326, 327, 438-441, Besucht am 16.06.2019',
			'2016') ON CONFLICT DO NOTHING;
INSERT INTO bibtex_db (cite_key, cite_type, title, author, journal, volume, "number", pages, publication_year)
	VALUES ('lecun-nature-15', 'article', 'Deep learning',
			'LeCun, Yann and Bengio, Yoshua and Hinton, Geoffrey', 'Nature', '521', '7553', '436-444', '2015') ON CONFLICT DO NOTHING;
INSERT INTO bibtex_db (cite_key, cite_type, author, title, journal, volume, publication_year, note)
	VALUES ('nvidia-tensor', 'article',
			'Stefano Markidis andSteven Wei Der Chien andErwin Laure andIvy Bo Peng andJeffrey S. Vetter',
			'{NVIDIA} Tensor Core Programmability, Performance {\&} Precision','CoRR','abs/1803.04014',
			'2018','http://arxiv.org/abs/1803.04014') ON CONFLICT DO NOTHING;
INSERT INTO bibtex_db (cite_key, cite_type, author, title, journal, volume, publication_year, note)
	VALUES ('cnn-inference-cpu', 'article',
			'Yizhi Liu and Yao Wang and Ruofei Yu and Mu Li and Vin Sharma and Yida Wang',
			'Optimizing {CNN} Model Inference on CPUs','CoRR','abs/1809.02697','2018','https://arxiv.org/abs/1809.02697v2') ON CONFLICT DO NOTHING;
INSERT INTO bibtex_db (cite_key, cite_type, author, title, journal, volume, publication_year, note)
	VALUES ('benchmark-article', 'article','Shaohuai Shi and Qiang Wang and Pengfei Xu and Xiaowen Chu',
			'Benchmarking State-of-the-Art Deep Learning Software Tools','CoRR','abs/1608.07249','2016','http://arxiv.org/abs/1608.07249') ON CONFLICT DO NOTHING;
INSERT INTO bibtex_db (cite_key, cite_type, author, title, journal, volume, publication_year, note)
	VALUES ('highly-scalable', 'article','Xianyan Jia and Shutao Song and Wei He and Yangzihao Wang and Haidong Rong
			and Feihu Zhou and Liqiang Xie anZhenyu Guo and Yuanzhou Yang and Liwei Yu and
			Tiegang Chen and Guangxiao Hu and Shaohuai Shi and Xiaowen Chu',
			'Highly Scalable Deep Learning Training System with Mixed-Precision: Training ImageNet in Four Minutes',
			'CoRR','abs/1807.11205','2018','http://arxiv.org/abs/1807.11205') ON CONFLICT DO NOTHING;
INSERT INTO bibtex_db (cite_key, cite_type, author, title, journal, volume, publication_year, note)
	VALUES ('gpu-mem-mngmnt', 'article','Junzhe Zhang and Sai{-}Ho Yeung and Yao Shu and Bingsheng He and Wei Wang',
			'Efficient Memory Management for GPU-based Deep Learning Systems','CoRR','abs/1903.06631','2019','http://arxiv.org/abs/1903.06631') ON CONFLICT DO NOTHING;
INSERT INTO bibtex_db (cite_key, cite_type, title, author, journal, volume, publication_year, pages)
	VALUES ('enrgyeffHWAccel', 'article','DianNao family: energy-efficient hardware accelerators for machine learning',
			'Yunji Chen and Tianshi Chen and Zhiwei Xu and Ninghui Sun and Olivier Temam','Commun. ACM','59','2016','105-112') ON CONFLICT DO NOTHING;
INSERT INTO bibtex_db (cite_key, cite_type, title, author, booktitle, editor, pages, publication_year, publisher, note)
	VALUES ('imagenet-class', 'incollection','ImageNet Classification with Deep Convolutional Neural Networks',
			'Alex Krizhevsky and Sutskever, Ilya and Hinton, Geoffrey E','Advances in Neural Information Processing Systems 25',
			'F. Pereira and C. J. C. Burges and L. Bottou and K. Q. Weinberger','1097--1105','2012','Curran Associates, Inc.',
			'http://papers.nips.cc/paper/4824-imagenet-classification-with-deep-convolutional-neural-networks.pdf') ON CONFLICT DO NOTHING;
INSERT INTO bibtex_db (cite_key, cite_type, title, journal, author, publication_year, "month", note)
	VALUES ('accel-ai-gpu', 'misc','Accelerating AI with GPUs: A New Computing Model','The Official NVIDIA Blog',
			'Huang, Jensen','2016','Jan',
			'\url{https://blogs.nvidia.com/blog/2016/01/12/accelerating-ai-artificial-intelligence-gpus/}, Besucht am 16.09.2019') ON CONFLICT DO NOTHING;
INSERT INTO bibtex_db (cite_key, cite_type, title, journal, author, publication_year, "month", note)
	VALUES ('intel-outperform', 'misc', 'Intel® CPU Outperforms NVIDIA* GPU on ResNet-50 Deep Learning Inference',
	'Intel® AI Developer Program', 'Haihao Shen and Feng Tian and Xu Deng and Cong Xu and Andres Rodriguez and Indu K. and Wei Li',
	'2019', 'May', '\url{https://software.intel.com/en-us/articles/intel-cpu-outperforms-nvidia-gpu-on-resnet-50-deep-learning-inference}, '' ||
	 ''Besucht am 16.09.2019') ON CONFLICT DO NOTHING;
INSERT INTO bibtex_db (cite_key, cite_type, title)
	VALUES ('booklet-test', 'booklet', 'I had no booklets') ON CONFLICT DO NOTHING;
INSERT INTO bibtex_db (cite_key, cite_type, author, title, booktitle, publication_year)
	VALUES ('inproceeding-test', 'inproceeding', 'inproceeding creator','I had no inproceedings',
			'inproceedings for beginner','2020') ON CONFLICT DO NOTHING;
INSERT INTO bibtex_db (cite_key, cite_type, author, title, booktitle, publication_year)
	VALUES ('conference-test', 'conference', 'conference creator','I had no conferences',
			'conference publishing inc.', 'conferences for dummies',  '2020') ON CONFLICT DO NOTHING;
INSERT INTO bibtex_db (cite_key, cite_type, author, title, publication_year, school)
	VALUES ('mastersthesis-test', 'mastersthesis', 'me', 'mastersthesis for beginners', '2020', 'HdM') ON CONFLICT DO NOTHING;
INSERT INTO bibtex_db (cite_key, cite_type, author, title, publication_year, school)
	VALUES ('phdthesis-test', 'phdthesis', 'me', 'phdthesis for beginners', '2020', 'HdM') ON CONFLICT DO NOTHING;
INSERT INTO bibtex_db (cite_key, cite_type, title, publication_year)
	VALUES ('proceedings-test', 'proceedings', 'proceedings for beginners', '2020') ON CONFLICT DO NOTHING;
INSERT INTO bibtex_db (cite_key, cite_type, author, title, publication_year, institution)
	VALUES ('techreport-test', 'techreport', 'me', 'techreports for beginners', '2020', 'HdM') ON CONFLICT DO NOTHING;
INSERT INTO bibtex_db (cite_key, cite_type, author, title, publication_year, note)
	VALUES ('unpublished-test', 'unpublished', 'me', 'unpublished for beginners', '2020', 'unpublished soontm') ON CONFLICT DO NOTHING

